<script setup></script>

<template>
  <div class="user_plus">
    <div class="user_info">
      <div class="info01">
        <div class="info_name">오승재님은</div>
        <div class="info_type">일반회원 입니다</div>
        <div class="change_buttonDiv">
          <button class="change_button">계정 타입 전환</button>
        </div>
      </div>
      <div class="info02">
        <ul class="info_ul">
          <li>
            <div class="li_line01">작성 가능한 리뷰</div>
            <div class="li_line02">1건</div>
          </li>
          <li>
            <div class="li_line01">내가 작성한 리뷰</div>
            <div class="li_line02">2건</div>
          </li>
        </ul>
      </div>
    </div>
    <div class="user_money"></div>
  </div>
</template>

<style scoped>
.user_plus {
  display: flex;
}

.user_info {
  display: flex;
  width: 43.75rem;
  height: 8.75rem;
  padding: 1.125rem 0rem 1.125rem 1.125rem;
  border-radius: 0.625rem;
  background-color: #fafafa;
}
.info01 {
  display: flex;
  flex-direction: column;
  width: 50%;
  height: 100%;
}
.info_name {
  display: flex;
  align-items: center;
  flex: 1;
  font-size: 1.25rem;
}
.info_type {
  display: flex;
  align-items: center;
  flex: 1;
  font-size: 1.25rem;
}
.change_buttonDiv {
  display: flex;
  flex: 1;
  align-items: center;
  justify-content: flex-end;
  padding-right: 3.125rem;
}
.change_button {
  font-size: 1.125rem;
  padding: 0 0.75rem;
  background-color: #fff;
  border-radius: 0.25rem;
  border: 0.0625rem solid #d9d9d9;
}
.info02 {
  width: 50%;
  height: 100%;
}
.info_ul {
  display: flex;
  width: 100%;
  height: 100%;
}
.info_ul li {
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  width: 50%;
  height: 100%;
  gap: 0.625rem;
}

.info_ul li:first-child {
  border-left: 1px solid #cecece;
  border-right: 1px solid #cecece;
}

.li_line01 {
  font-size: 1.25rem;
}

.li_line02 {
  font-size: 1.25rem;
}
</style>
