<script setup>
import ClientRezs from "./components/ClientRezs.vue";
</script>

<template>
  <div class="my_storeRezs">
    <div class="storeRez_title">식당 예약 내역</div>
    <div class="storeRez_contents">
      <ClientRezs></ClientRezs>
      <!-- 리뷰 항목 -->
    </div>
  </div>
</template>

<style scoped>
.storeRez_title {
  display: flex;
  font-size: 1.5rem;
  font-weight: bold;
  margin-bottom: 1.25rem;
}
.storeRez_contents {
  border-radius: 1rem;
  border: 1px solid #ccc;
  margin: 1rem 0;
}
</style>
