<script setup>
import PlanEach from '@/components/plan/PlanEach.vue'
</script>

<template>
  <div>
    <div class="planListTitle">
      <h1>내가 세운 여행 계획들을 확인해보세요</h1>
    </div>
    <div class="switch">
      <div class="switch-holder">
        <div class="switch-label">
          <i class="fa fa-pencil-square-o" aria-hidden="true"></i
          ><span>리뷰를 작성한 계획만 보기</span>
        </div>
        <div class="switch-toggle">
          <input type="checkbox" id="onArticle" v-model="onArticleModel" />
          <label for="onArticle"></label>
        </div>
      </div>
    </div>

    <div class="container1" v-if="!onArticleModel">
      <div v-for="plan in plans" :key="plan['plan'].planIdx">
        <PlanEach :plan="plan" id="testBtn" v-if="plan.hasArticle == 0"></PlanEach>
        <!-- <PlanDetail :plan2="plan"></PlanDetail> -->
      </div>
    </div>
    <div class="container1" v-else-if="onArticleModel">
      <div v-for="plan in plans" :key="plan['plan'].planIdx">
        <PlanEach :plan="plan" id="testBtn" v-if="plan.hasArticle == 1"></PlanEach>
        <!-- <PlanDetail :plan2="plan"></PlanDetail> -->
      </div>
    </div>
  </div>
</template>
<style scoped>
.container1 {
  background: var(--color-background);
  position: relative;
  width: 80%;
  margin: 40px auto 100px auto;
  display: flex;
  align-items: center;
  justify-content: flex-start;
  flex-wrap: wrap;
}

.planListTitle {
  position: relative;
  width: 80%;
  margin: 80px auto 0;
}
</style>
