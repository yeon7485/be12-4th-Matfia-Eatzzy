<script setup>
const props = defineProps({
  menu: {
    type: Object,
    required: true,
  },
});
</script>

<template>
  <div class="menu_box">
    <h2>메뉴판</h2>
    <ul>
      <li v-for="menu in props.menu" class="menu_list">
        <p>{{ menu.name }}</p>
        <div class="menu_dots"></div>
        <p>17,000원</p>
      </li>
    </ul>
  </div>
</template>

<style scoped>
.menu_box {
  margin: 0 auto;
  border: 1px solid #b6bdc7;
  border-radius: 1rem;
  padding: 2rem;
  box-sizing: border-box;
}

.menu_box > h2 {
  font-size: 1.3rem;
  margin-bottom: 1rem;
}

.menu_box > ul {
  padding: 0.5rem;
}

.menu_list {
  width: 100%;
  display: flex;
  justify-content: space-between;
  margin-top: 1rem;
  font-size: 0.9rem;
}

.menu_list:first-child {
  margin-top: 0;
}

.menu_dots {
  flex-grow: 1;
  height: 1px;
  margin: auto 1rem;
  border-top: 1px dashed #ccc;
}
</style>
