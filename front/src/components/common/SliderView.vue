<script setup></script>

<template>
  <div id="demo" class="carousel slide" data-bs-ride="carousel">
    <!-- Indicators/dots -->
    <div class="carousel-indicators">
      <button type="button" data-bs-target="#demo" data-bs-slide-to="0" class="active"></button>
      <button type="button" data-bs-target="#demo" data-bs-slide-to="1"></button>
      <button type="button" data-bs-target="#demo" data-bs-slide-to="2"></button>
    </div>

    <!-- The slideshow/carousel -->
    <div class="carousel-inner">
      <div class="carousel-item active">
        <!-- Slide 1 -->
        <div class="slide1 d-flex justify-content-center">
          <div class="container align-self-center">
            <h2 class="">EnjoyTrip을 방문해주셔서 감사합니다!</h2>
            <p class="">
              여행을 떠나고 싶으신가요? <br />
              당신에게 맞는 여행지를 발견해보세요.
            </p>
            <a href="#searchAbout" class="btn-get-started">여행지 구경 가기</a>
          </div>
        </div>
      </div>
      <div class="carousel-item">
        <div class="slide2 d-flex justify-content-center">
          <div class="container align-self-center">
            <h2 class="InDown">인기있는 여행지를 만나보세요</h2>
            <p class="InUp">생각지도 못한 곳을 발견할 수도 있어요.</p>
            <a href="/article/list" class="btn-get-started">금주의 인기 여행기 보기</a>
          </div>
        </div>
      </div>
      <div class="carousel-item">
        <div class="slide3 d-flex justify-content-center">
          <div class="container align-self-center">
            <h2 class="InDown">당신의 여행 계획은 어떠셨나요 ?</h2>
            <p class="InUp">당신의 여행에 후기를 함께하고싶어요</p>
            <a href="/article/write" class="btn-get-started">여행 후기 쓰기</a>
          </div>
        </div>
      </div>
    </div>

    <!-- Left and right controls/icons -->
    <button class="carousel-control-prev" type="button" data-bs-target="#demo" data-bs-slide="prev">
      <span class="carousel-control-prev-icon"></span>
    </button>
    <button
      class="carousel-control-next"
      type="button"
      data-bs-target="#demo"
      data-bs-slide="next"
    >
      <span class="carousel-control-next-icon"></span>
    </button>
  </div>
</template>

<style>
a {
  text-decoration: none;
}
.btn-get-started {
  font-weight: 500;
  font-size: 14px;
  letter-spacing: 1px;
  display: inline-block;
  line-height: 1;
  color: rgb(255, 255, 255);
  animation-delay: 0.8s;
  padding: 12px 32px;
  border-radius: 5px;
  transition: all 0.5s ease 0s;
  margin: 10px;
  border-width: 0px;
  border-style: initial;
  border-color: initial;
  border-image: initial;
  background: var(--color-blue);
}

.slide1 {
  width: 100%;
  background-position: center;
  background: url(https://img.freepik.com/free-vector/concept-banner-turism-realistic-style-with-map-pointer-road-sign-suitcase-camera-vector-illustration_548887-208.jpg?w=1380&t=st=1700585516~exp=1700586116~hmac=ade8dede941e8aa3b5c45f1691d884e82e1ef02d2fcc15a7a5625533f961fdf2);
  /* background-size: cover; */
}
.slide2 {
  width: 100%;
  background-position: center;
  background: url(https://img.freepik.com/free-vector/summer-background-with-pool-view-isometric-style_23-2147807076.jpg?w=900&t=st=1684936570~exp=1684937170~hmac=dbbc66d55e6134e95ca14d1012f87e0ce5e754bc28bf481d4fe526c649e7cc13);
  /* background-size: cover; */
}
.slide3 {
  width: 100%;
  background-position: center;
  background: url(https://img.freepik.com/free-vector/seoul-korea-city-skyline-white-background-flat-vector-illustration-business-travel-tourism-concept-with-modern-buildings-image-banner-website_596401-6.jpg?w=1480&t=st=1700584158~exp=1700584758~hmac=7fcf1b68151af77ee6b226f51a2eb3a8ac909cd269497ce3b56d966d864c4287);
}

/* .slide1::before, */
.slide2::before {
  content: '';
  opacity: 0.65;
  position: absolute;
  top: 0px;
  left: 0px;
  right: 0px;
  bottom: 0px;
  background-color: #000;
}

/* .slide1 *, */
.slide2 * {
  color: #fff;
  text-align: center;
  position: relative;
  z-index: 100;
}

.slides h2 {
  font-weight: 700;
}
</style>
