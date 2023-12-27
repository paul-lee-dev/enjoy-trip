<script setup>
import TopButtonView from '@/components/icons/TopButtonView.vue'
import ArticleView from '@/views/ArticleView.vue'

import SliderView from '@/components/common/SliderView.vue'
import MapAttractionInfo from '@/components/map/MapAttractionInfo.vue'
import VkakaoMap from '@/components/common/VkakaoMap.vue'
</script>

<template>
  <div class="hello">
    <section id="hero">
      <div class="hero-container">
        <div id="hero">
          <SliderView></SliderView>
        </div>
      </div>
    </section>
    <!-- End Hero -->
    <!-- 
    <Swiper></Swiper> -->

    <!-- ======= 추천 여행지  ======= -->
    <section id="aboutArticle" class="about">
      <div class="section-title d-flex align-items-center">
        <h2 class="align-self-center">추천 여행지</h2>
        <button class="btn-get-started">
          <router-link to="/article/list">더 살펴보기</router-link>
        </button>
      </div>

      <p class="section-title d-flex">최근 사랑받은 여행후기들을 만나보세요</p>

      <div id="hotplaceArea">
        <span v-for="article in articles" :key="article['articleIdx']">
          <ArticleView
            :article="article"
            @click="articleDetail(article.articleIdx, $event)"
          ></ArticleView>
        </span>
      </div>
    </section>
    <!-- End 여기어때 -->

    <!-- 어디든 좋아요======= -->
    <section id="searchAbout" class="portfolio section-bg">
      <div class="searchAbout">
        <div class="section-title">
          <h2>어디든 좋아요 🙋‍♀️</h2>
          <p class="mt-2">클릭하는 그 곳이 당신의 여행지가 될 거에요</p>
        </div>
        <div class="sectionSearch">
          <MapAttractionInfo></MapAttractionInfo>
          <VkakaoMap></VkakaoMap>
          <button class="btn-get-started">
            <router-link to="/plan/create">여행지가 마음에 드시나요 ?</router-link>
          </button>
        </div>
      </div>
    </section>
    <TopButtonView></TopButtonView>
  </div>
</template>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
.btn-get-started a {
  text-decoration: none;
  color: white;
}
h2 {
  margin: 0;
  font-weight: bold;
}
h3 {
  margin: 40px 0 0;
}
ul {
  list-style-type: none;
  padding: 0;
}
li {
  margin: 0 10px;
}
a {
  color: var(--color-blue);
  text-decoration: none;
}

#aboutArticle,
#searchAbout {
  margin-top: 100px;
}

.sectionSearch {
  width: 80%;
  margin: auto;
}

.section-title {
  margin-left: 200px;
  text-align: left;
}

#hotplaceArea {
  margin-left: 180px;
  margin-right: 100px;
  display: flex;
  flex-wrap: wrap;
  height: 100%;
  justify-content: flex-start;
}

.dataImg {
  width: 400px !important;
}

#hero {
  position: relative;
  top: -15px;
}
</style>
