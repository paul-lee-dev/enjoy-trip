<script setup>
import Vselect from '@/components/common/Vselect.vue'
import VkakaoMap from '@/components/common/VkakaoMap.vue'
import MapAttractionInfo from '@/components/map/MapAttractionInfo.vue'
</script>

<template>
  <div class="hello">
    <!-- #f5f9fc -->
    <div class="selectAll" data-aos="fade-up">
      <div class="row">
        <div class="mt-5">
          <div class="">
            <h2 class="text-center fw-bold">🚗나의 여행 계획🚗</h2>
            <p class="text-center fst-normal">나만의 여행길을 그려보세요!</p>
            <div class="d-flex justify-content-center mt-5">
              <input
                id="title"
                class="px-2"
                placeholder=" 여행 계획에 이름을 붙여주세요"
                value="제목테스트"
              />
            </div>
            <div class="d-flex justify-content-center mt-3 align-items-center">
              <label class="me-2" style="font-weight: bold; font-size: 16px">출발일</label
              ><input type="date" id="startDate" value="2023-05-03" data-placeholder="날짜 선택" />
              <div><i class="fa fa-exchange" aria-hidden="true"></i></div>
              <label class="me-2" style="font-weight: bold; font-size: 16px">도착일</label
              ><input type="date" id="endDate" value="2023-05-04" data-placeholder="날짜 선택" />
              <button type="button" class="btn-get-started" id="dateBtn" @click="planDate">
                확인
              </button>
            </div>
            <div>
              <div class="selectDate select-box mt-3" style="display: none">
                <h6 style="color: black">작성할 계획일자를 선택하고 장소를 추가하세요</h6>
                <div class="planDate options-container" style="z-index: 101; position: relative">
                  <div class="option">
                    <input type="radio" class="radio" name="category" value="0" />
                    <label for="">Select Date</label>
                  </div>
                </div>
                <div class="selected" id="selectedContent" v-on:click="selectDate" value="0">
                  Select Date
                </div>
              </div>
              <Vselect></Vselect>
            </div>
            <!-- sectionSearch  -->
            <div class="row mt-5">
              <div class="col-md-8 leftBox"><VkakaoMap></VkakaoMap></div>
              <div class="col-md-3 rightBox">
                <MapAttractionInfo></MapAttractionInfo>
                <button class="btn-get-started" type="button" id="addPlanBtn" @click="addPlan">
                  추가
                </button>
              </div>
            </div>
          </div>
          <div></div>
        </div>
      </div>
    </div>

    <section class="container mt-5" id="dailyPlans">
      <h1>Plans</h1>
    </section>

    <div style="margin-top: 20px; display: flex; justify-content: center">
      <button
        class="btn-get-started"
        id="resultBtn"
        style="text-align: center"
        @click="submitResult"
      >
        최종 등록
      </button>
    </div>
    <TopButton></TopButton>
  </div>
</template>

<style>
#title {
  width: 400px;
  border: 0;
  z-index: 1;
  background-color: transparent;
  border-bottom: 2px solid #eee;
  font: inherit;
  font-size: 1.125rem;
  padding: 0.25rem 0;
}

#title:focus,
#title:valid {
  outline: 0;
  border-bottom-color: var(--color-blue);
}

#title:valid + .input-label {
  color: var(--color-blue);
  transform: translateY(-1.5rem);
}
#startDate,
#endDate,
.fa-exchange {
  border: none;
  padding: 8px 15px;
  margin-right: 15px;
  border-radius: 5px;
  text-align: center;
}
.hello {
  background: var(--color-background);
  margin-top: 30px;
}

.row {
  display: flex;
  align-items: flex-start;
  flex-wrap: wrap;
  margin: auto;
  justify-content: center;
}

.mapBox {
  width: 80%;
  margin: auto;
}

.rightBox,
.leftBox {
  background: var(--color-white);
  margin: 0px 10px;
  box-shadow: 0px 1px 15px 1px rgba(69, 65, 78, 0.08);
  border: 1px solid #eee;
  min-height: 562px;
  height: 600px;
}

.mapContent {
  width: 100%;
  margin: 30px auto;
}

.dataImg {
  width: 80%;
}

.selectAll h2 {
  margin: 0;
  font-weight: bold;
}
h3 {
  margin: 40px 0 0;
}

a {
  color: #42b983;
  text-decoration: none;
}

h1 {
  width: fit-content;
  text-transform: uppercase;
  font-weight: 700;
  font-size: 30px;
  border-left: 10px solid var(--color-blue);
  padding-left: 10px;
  margin: 30px 0px;
}

.selectDate {
  margin: auto;
}

.select-box {
  display: flex;
  width: 300px;
  flex-direction: column;
}

.select-box .options-container {
  background: var(--color-lightgray);
  max-height: 0;
  width: 100%;
  opacity: 0;
  transition: all 0.4s;
  border-radius: 8px;
  overflow: hidden;
  order: 1;
}

.selected {
  /* main 에 보이는 select box */
  background-color: var(--color-white);
  box-shadow: 0px 1px 15px 1px rgba(69, 65, 78, 0.08);
  border: 1px solid #eee;
  /* border: 2px solid var(--color-darkgray); */
  border-radius: 8px;
  /* margin-bottom: 8px; */
  color: var(--color-black);
  font-weight: bold;
  position: relative;
  order: 0;
}

.selected::after {
  content: '';
  /* background: url("@/assets/img/arrow-down.svg"); */
  background: url('data:image/svg+xml;utf8,<svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 448 512"><!-- Font Awesome Pro 5.15.4 by @fontawesome - https://fontawesome.com License - https://fontawesome.com/license (Commercial License) --><path d="M207.029 381.476L12.686 187.132c-9.373-9.373-9.373-24.569 0-33.941l22.667-22.667c9.357-9.357 24.522-9.375 33.901-.04L224 284.505l154.745-154.021c9.379-9.335 24.544-9.317 33.901.04l22.667 22.667c9.373 9.373 9.373 24.569 0 33.941L240.971 381.476c-9.373 9.372-24.569 9.372-33.942 0z"/></svg>');
  background-repeat: no-repeat;
  opacity: 20%;
  position: absolute;
  top: 17px;
  height: 30%;
  width: 32px;
  right: 10px;
  background-position: center;
  transition: all 0.4s;
}

.select-box .options-container.active {
  max-height: 240px;
  opacity: 1;
  overflow-y: scroll;
}

.select-box .options-container.active + .selected::after {
  transform: rotateX(180deg);
}

.select-box .options-container::-webkit-scrollbar {
  width: 8px;
  background: var(--color-lightgray);
  border-radius: 0 8px 8px 0;
}

.select-box .options-container::-webkit-scrollbar-thumb {
  background: var(--color-semigray);
  border-radius: 0 8px 8px 0;
}

.select-box .option,
.selected {
  padding: 12px 24px;
  cursor: pointer;
}

.select-box .option:hover {
  background: var(--color-semigray);
}

.select-box label {
  cursor: pointer;
}

.select-box .option .radio {
  display: none;
}

/* 시작 */
.fl-left {
  float: left;
}

.fl-right {
  float: right;
}

.row {
  overflow: hidden;
  box-sizing: border-box;
}

.card {
  display: table-row;
  width: 49%;
  background-color: var(--color-white);
  color: var(--color-background);
  margin-bottom: 10px;
  text-transform: uppercase;
  border-radius: 4px;
  position: relative;
  padding: 0;
  border: 0;
  box-sizing: border-box;
  box-shadow: 0px 1px 15px 1px rgba(69, 65, 78, 0.08);
}

.card + .card {
  margin-left: 2%;
}

.date {
  display: table-cell;
  width: 200px;
  position: relative;
  text-align: center;
  border-right: 2px dashed var(--color-background);
}

.date:before,
.date:after {
  content: '';
  display: block;
  width: 30px;
  height: 30px;
  background-color: var(--color-background);
  position: absolute;
  top: -15px;
  right: -15px;
  z-index: 1;
  border-radius: 50%;
}

.date:after {
  top: auto;
  bottom: -15px;
}

.date time {
  display: block;
  position: absolute;
  top: 50%;
  left: 50%;
  -webkit-transform: translate(-50%, -50%);
  -ms-transform: translate(-50%, -50%);
  transform: translate(-50%, -50%);
}

.date time span {
  display: block;
}

.date time span:first-child {
  color: var(--color-black);
  font-weight: 600;
  font-size: 250%;
}

.date time span:last-child {
  text-transform: uppercase;
  font-weight: 600;
  margin-top: -10px;
}

.card-cont {
  display: table-cell;
  width: 75%;
  font-size: 85%;
  padding: 10px 10px 30px 50px;
  height: 200px;
}

.row:last-child .card:last-of-type .card-cont h3 {
  text-decoration: line-through;
}

.card-cont > div {
  display: table-row;
}

.card-cont .even-date i,
.card-cont .even-info i,
.card-cont .even-date time,
.card-cont .even-info p {
  display: table-cell;
}

.card-cont .even-date i,
.card-cont .even-info i {
  padding: 5% 5% 0 0;
}

.card-cont .even-info p {
  padding: 30px 30px 0 10px;
  font-size: 17px;
  font-weight: 500;
}

.card-cont .even-date time span {
  display: block;
}

#deleteBtn {
  border: none;
  padding: 7px 10px;
  border-radius: 5px;
  background-color: #d40b0b;
  color: var(--color-white);
}

@media screen and (max-width: 860px) {
  .card {
    display: block;
    float: none;
    width: 100%;
    margin-bottom: 10px;
  }
  .card + .card {
    margin-left: 0;
  }
  .card-cont .even-date,
  .card-cont .even-info {
    font-size: 75%;
  }
}
</style>
