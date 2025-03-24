<script setup>
import { onMounted } from "vue";
import { useCategoryStore } from "../../../stores/useCategoryStore";

const categoryStore = useCategoryStore();

const changeBigCate = (cate) => {
  categoryStore.setBigCate(cate);
};

onMounted(async () => {
  await categoryStore.getBigCategory();
});
</script>

<template>
  <ul class="category_list">
    <li
      class="big_category"
      :class="{ picked: categoryStore.bigCate.idx === 0 }"
      @click="changeBigCate({ idx: 0, name: '전체' })"
    >
      전체
    </li>

    <li
      v-for="cate in categoryStore.bigCategory"
      :key="cate.idx"
      class="big_category"
      :class="{ picked: categoryStore.bigCate.idx === cate.idx }"
      @click="changeBigCate(cate)"
    >
      {{ cate.name }}
    </li>
  </ul>
</template>

<style scoped>
.category_list {
  display: grid;
  grid-template-columns: repeat(8, 1fr);
  border-bottom: 1px solid #cecece;
  margin-bottom: 1.25rem;
  box-sizing: border-box;
}
.big_category {
  padding: 1rem;
  border-bottom: 2px solid transparent;
  display: flex;
  justify-content: center;
  align-items: center;
  cursor: pointer;
  font-size: 1.2rem;
  font-weight: 500;
  color: #666970;
  white-space: nowrap;
  box-sizing: border-box;
}

.big_category:active,
.big_category:hover {
  font-weight: 700;
  color: #ff7400;
  border-bottom: 2px solid #ff7400;
}

.big_category.picked {
  color: #ff7400;
  font-weight: 700;
}

@media (max-width: 960px) {
  .category_list {
    grid-template-columns: repeat(4, 1fr);
  }
}
</style>
