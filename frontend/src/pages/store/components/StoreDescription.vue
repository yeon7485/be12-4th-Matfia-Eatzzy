<script setup>
const props = defineProps({
  description: {
    type: String,
    required: true,
  },
});
</script>

<template>
  <div class="description_box">
    <h2>매장 소개</h2>
    <p>
      {{ props.description }}
    </p>
  </div>
</template>

<style scoped>
/* 매장 소개 */
.description_box {
  padding: 1rem 1.5rem;
}

.description_box > h2 {
  font-size: 1.2rem;
  margin-bottom: 0.8rem;
}

.description_box > p {
  font-size: 1rem;
}
</style>
