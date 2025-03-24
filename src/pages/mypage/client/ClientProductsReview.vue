<script setup>
import { onMounted } from "vue";
import ClientPAReviews from "./components/ClientPAReviews.vue";
import ClientPBReviews from "./components/ClientPBReviews.vue";

onMounted(() => {
  document.querySelectorAll(".tab_button").forEach((button) => {
    button.addEventListener("click", () => {
      const targetTab = button.dataset.tab; // 선택된 탭 ID

      // 모든 버튼에서 active 클래스 제거
      document.querySelectorAll(".tab_button").forEach((btn) => btn.classList.remove("active"));

      // 모든 콘텐츠에서 active 클래스 제거
      document.querySelectorAll(".tab_content").forEach((content) => content.classList.remove("active"));

      // 현재 클릭된 버튼에 active 클래스 추가
      button.classList.add("active");

      // 연결된 콘텐츠에 active 클래스 추가
      document.querySelector(`#${targetTab}`).classList.add("active");
    });
  });
});
</script>

<template>
  <div class="myProduct_Review">
    <div class="product_header">상품 리뷰</div>
    <div class="my_reviewTab">
      <button class="tab_button active" data-tab="tab1">작성가능한 리뷰</button>
      <button class="tab_button" data-tab="tab2">내가 작성한 리뷰</button>
    </div>
    <div class="my_reviewTabContent">
      <div id="tab1" class="tab_content active">
        <div class="review_contents">
          <ClientPBReviews></ClientPBReviews>
        </div>
      </div>
      <div id="tab2" class="tab_content">
        <div class="review_contents">
          <ClientPAReviews></ClientPAReviews>
        </div>
      </div>
    </div>
  </div>
</template>

<style scoped>
.product_header {
  font-size: 1.5rem;
  font-weight: bold;
  margin-bottom: 1.25rem;
}
.my_reviewTab {
  display: flex;
  gap: 1.875rem;
  border-bottom: 1px solid #ccc;
}
.tab_button {
  font-size: 1.125rem;
  padding: 0.625rem 0.1875rem;
  font-weight: normal; /* 기본 상태에서 글자 굵기 */
  cursor: pointer;
}
.tab_button.active {
  font-weight: bold; /* 활성화된 상태에서 글자 굵기 */
  color: black;
  border-bottom: 2px solid black;
}
.tab_content {
  display: none;
}

.tab_content.active {
  display: block;
}
.my_reviewTabContent {
  border-radius: 1rem;
  border: 1px solid #ccc;
  margin: 1rem 0;
}
</style>
