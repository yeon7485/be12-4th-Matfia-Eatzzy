package com.example.appapi;


import com.example.appapi.category.CategoryRepository;
import com.example.appapi.category.model.Category;
import com.example.appapi.orders.OrdersRepository;
import com.example.appapi.orders.model.Orders;
import com.example.appapi.orders.orderProducts.OrderProductsRepository;
import com.example.appapi.orders.orderProducts.model.OrderProducts;
import com.example.appapi.paymentmethod.model.PaymentMethod;
import com.example.appapi.product.images.model.ProductsImages;
import com.example.appapi.product.images.repository.ProductsImagesRepository;
import com.example.appapi.product.model.Products;
import com.example.appapi.product.repository.ProductsRepository;
import com.example.appapi.product.review.images.model.ProductReviewImages;
import com.example.appapi.product.review.images.repository.ProductReviewImagesRepository;
import com.example.appapi.product.review.model.ProductReviews;
import com.example.appapi.product.review.repository.ProductReviewsRepository;
import com.example.appapi.store.StoreRepository;
import com.example.appapi.store.model.AllowedStatus;
import com.example.appapi.store.model.Store;
import com.example.appapi.users.UsersRepository;
import com.example.appapi.users.model.UserType;
import com.example.appapi.users.model.Users;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class InitDb {
    private final UsersRepository usersRepository;
    private final ProductsRepository productsRepository;
    private final ProductReviewsRepository productReviewsRepository;
    private final PasswordEncoder passwordEncoder;
    private final ProductsImagesRepository productsImagesRepository;
    private final ProductReviewImagesRepository productReviewImagesRepository;
    private final OrdersRepository ordersRepository;
    private final OrderProductsRepository orderProductsRepository;
    private final CategoryRepository categoryRepository;
    private final StoreRepository storeRepository;

    @PostConstruct
    public void dataInsert() {
        Map<Long, Category> categoryMap = new HashMap<>();

        // Insert big categories
        Category korean = categoryRepository.save(Category.builder().name("한식").build());
        Category chinese = categoryRepository.save(Category.builder().name("중식").build());
        Category western = categoryRepository.save(Category.builder().name("양식").build());
        Category japanese = categoryRepository.save(Category.builder().name("일식").build());
        Category snack = categoryRepository.save(Category.builder().name("분식").build());
        Category asian = categoryRepository.save(Category.builder().name("아시아").build());
        Category fastFood = categoryRepository.save(Category.builder().name("패스트푸드").build());

        categoryMap.put(1L, korean);
        categoryMap.put(2L, chinese);
        categoryMap.put(3L, western);
        categoryMap.put(4L, japanese);
        categoryMap.put(5L, snack);
        categoryMap.put(6L, asian);
        categoryMap.put(7L, fastFood);

        // Insert small categories
        categoryMap.put(8L, categoryRepository.save(Category.builder().name("찜/탕").parentCategory(korean).build()));
        categoryMap.put(9L, categoryRepository.save(Category.builder().name("고기/구이").parentCategory(korean).build()));
        categoryMap.put(10L, categoryRepository.save(Category.builder().name("족발/보쌈").parentCategory(korean).build()));
        categoryMap.put(11L, categoryRepository.save(Category.builder().name("백반/죽").parentCategory(korean).build()));
        categoryMap.put(12L, categoryRepository.save(Category.builder().name("도시락").parentCategory(korean).build()));

        PaymentMethod easyMethod = PaymentMethod.builder()
                .name("EASY_PAY")
                .build();


        for (int i = 1; i <= 3; i++) {
            Users user = Users.builder()
                    .email("test0" + i + "@test.com")
                    .password(passwordEncoder.encode("qwer1234"))
                    .phone("123456789")
                    .address("test address")
                    .userId("test0" + i)
                    .addressDetail("test address detail")
                    .birthDate("test")
                    .name("test0"+i)
                    .userType(UserType.SELLER)
                    .build();

            usersRepository.save(user);

            for (int j = 1; j <= 6; j++) {
                Products products = productsRepository.save(Products.builder()
                        .user(user)
                        .name("상품-" + (j + 5 * (i - 1)))
                        .stock(10)
                                .price(j * 10000)
                        .description("상품-" + (j + 5 * (i - 1)) + " by test0" + i + "@test.com")
                        .build());
                productsImagesRepository.save(
                        ProductsImages.builder()
                                .products(products)
                                .imagePath("https://godomall.speedycdn.net/ec5d2a1c8483712efb957784c858b320/goods/1000000463/image/detail/1000000463_detail_020.jpg")
                                .build()
                );
            }
        }


        Users user1 = usersRepository.findById(1L).orElseThrow();

        // Insert stores

        // Insert stores
        storeRepository.save(Store.builder().name("고도식").category(categoryMap.get(9L)).user(user1).shortAddress("서울 송파구").starPoint(4.9).reviewCnt(38).likesCount(30L).allowed(AllowedStatus.YES).build());
        storeRepository.save(Store.builder().name("장뜰떡볶이").category(categoryMap.get(5L)).user(user1).shortAddress("서울 동작구").starPoint(4.7).reviewCnt(53).likesCount(35L).allowed(AllowedStatus.YES).build());
        storeRepository.save(Store.builder().name("돝고기506").category(categoryMap.get(9L)).user(user1).shortAddress("서울 강남구").starPoint(4.3).reviewCnt(33).likesCount(27L).allowed(AllowedStatus.YES).build());
        storeRepository.save(Store.builder().name("팀호완").category(categoryMap.get(2L)).user(user1).shortAddress("서울 강남구").starPoint(4.2).reviewCnt(61).likesCount(23L).allowed(AllowedStatus.YES).build());
        storeRepository.save(Store.builder().name("온량").category(categoryMap.get(3L)).user(user1).shortAddress("서울 성동구").starPoint(4.5).reviewCnt(36).likesCount(28L).allowed(AllowedStatus.YES).build());
        storeRepository.save(Store.builder().name("울프강 스테이크하우스").category(categoryMap.get(3L)).user(user1).shortAddress("서울 중구").starPoint(4.6).reviewCnt(45).likesCount(29L).allowed(AllowedStatus.YES).build());
        storeRepository.save(Store.builder().name("호족반 도산공원점").category(categoryMap.get(1L)).user(user1).shortAddress("서울 강남구").starPoint(4.8).reviewCnt(32).likesCount(40L).allowed(AllowedStatus.YES).build());
        storeRepository.save(Store.builder().name("피자헤븐").category(categoryMap.get(3L)).user(user1).shortAddress("서울 강동구").starPoint(4.4).reviewCnt(28).likesCount(33L).allowed(AllowedStatus.YES).build());
        storeRepository.save(Store.builder().name("쭈꾸미촌").category(categoryMap.get(12L)).user(user1).shortAddress("서울 관악구").starPoint(4.3).reviewCnt(50).likesCount(21L).allowed(AllowedStatus.YES).build());
        storeRepository.save(Store.builder().name("서울브루어리").category(categoryMap.get(3L)).user(user1).shortAddress("서울 종로구").starPoint(4.7).reviewCnt(41).likesCount(38L).allowed(AllowedStatus.YES).build());
        storeRepository.save(Store.builder().name("맵떡이네").category(categoryMap.get(5L)).user(user1).shortAddress("서울 서대문구").starPoint(4.5).reviewCnt(57).likesCount(36L).allowed(AllowedStatus.YES).build());
        storeRepository.save(Store.builder().name("황제횟집").category(categoryMap.get(12L)).user(user1).shortAddress("서울 영등포구").starPoint(4.6).reviewCnt(62).likesCount(28L).allowed(AllowedStatus.YES).build());

        for(int i=1;i<=3;i++){
            Users user = usersRepository.findById(Long.valueOf(i)).get();
            Orders orders = Orders.builder()
                    .user(user)
                    .price(i * 10000)
                    .message("테스트 주문")
                    .status("결제 대기")
                    .build();

            ordersRepository.save(orders);

            Products products = productsRepository.findByIdx(Long.valueOf(i));
            OrderProducts orderProducts = OrderProducts.builder()
                    .quantity(1)
                    .orders(orders)
                    .products(products)
                    .build();
            orderProductsRepository.save(orderProducts);

            for (int j = 1; j <= 6; j++) {
                ProductReviews productReviews = ProductReviews.builder()
                        .products(Products.builder()
                                .idx(Long.valueOf(j))
                                .build())
                        .content("상품" + j + "에 대한 리뷰")
                        .user(Users.builder()
                                .idx(Long.valueOf(i))
                                .build())
                        .build();

                ProductReviews save = productReviewsRepository.save(productReviews);
                ProductReviewImages savedImages = ProductReviewImages.builder()
                        .imagePath("https://d12zq4w4guyljn.cloudfront.net/20240925123559_photo1_bb072b2e6070.jpg")
                        .productReviews(save)
                        .build();
                productReviewImagesRepository.save(savedImages);
            }
        }


    }
}
