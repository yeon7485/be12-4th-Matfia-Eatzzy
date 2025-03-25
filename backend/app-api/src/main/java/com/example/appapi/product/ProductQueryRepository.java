package com.example.appapi.product;

import com.example.appapi.category.model.QCategory;
import com.example.appapi.product.images.model.QProductsImages;
import com.example.appapi.product.model.Products;
import com.example.appapi.product.model.ProductsDto;
import com.example.appapi.product.model.QProducts;
import com.example.appapi.store.model.Store;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.PathBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class ProductQueryRepository {
    private final JPAQueryFactory queryFactory;
    private final QProducts products;
    private final QCategory category;
    private final QProductsImages productsImages;

    public ProductQueryRepository(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
        this.products = QProducts.products;
        this.category = QCategory.category;
        this.productsImages = QProductsImages.productsImages;
    }
    public Slice<ProductsDto.InfoResponse> search(int page, int size, String sort, Long categoryIdx) {
        BooleanBuilder builder = new BooleanBuilder();
        OrderSpecifier<?> orderSpecifier = getOrderSpecifier(sort);

        if (categoryIdx != null) {
            builder.and(products.category.idx.eq(categoryIdx));
        }

        List<Products> productsList = queryFactory.selectFrom(products)
                .join(products.category, category)
                .join(products.images, productsImages)
                .where(builder)
                .groupBy(products.idx)
                .orderBy(orderSpecifier)
                .offset((long) page * size)
                .limit(size + 1) // ✅ 다음 페이지 여부 확인을 위해 size + 1 개 가져오기
                .fetch();

        // DTO 변환
        List<ProductsDto.InfoResponse> dtoList = productsList.stream()
                .map(ProductsDto.InfoResponse::fromEntity)
                .collect(Collectors.toList());

        // ✅ Slice 처리를 위해 추가 개수 확인
        boolean hasNext = false;
        if (dtoList.size() > size) {
            dtoList.remove(dtoList.size() - 1); // ✅ 실제 응답에서는 초과된 1개 삭제
            hasNext = true; // ✅ 다음 페이지가 존재함을 의미
        }

        return new SliceImpl<>(dtoList, PageRequest.of(page, size), hasNext);
    }


    // 정렬 처리
    private OrderSpecifier<?> getOrderSpecifier(String sort) {
        PathBuilder<Products> pathBuilder = new PathBuilder<>(Products.class, "store");

        if (sort == null) {
            sort = "idx";
        }

        return switch (sort.toLowerCase()) {
            case "reviewcount" -> new OrderSpecifier<>(Order.DESC, pathBuilder.getNumber("reviewCount", Integer.class));
            case "starpoint" -> new OrderSpecifier<>(Order.DESC, pathBuilder.getNumber("starPoint", Double.class));
            default -> new OrderSpecifier<>(Order.ASC, pathBuilder.getNumber("idx", Long.class)); // 기본 정렬 idx ASC
        };
    }
}
