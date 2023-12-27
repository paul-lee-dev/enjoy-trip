<script></script>

<template>
  <div class="card2">
    <div class="card2-body">
      <h1 class="ms-3">프로필 사진을 선택해보세요</h1>
      <div class="row mb-3">
        <div v-bind:class="{ 'card-img': true, active: nowImg.userImageUrl == 1 }">
          <img id="1" src="@/assets/img/profile/1.jpg" @click="selectImg" />
        </div>
        <div v-bind:class="{ 'card-img': true, active: nowImg.userImageUrl == 2 }">
          <img id="2" src="@/assets/img/profile/2.jpg" @click="selectImg" />
        </div>
        <div v-bind:class="{ 'card-img': true, active: nowImg.userImageUrl == 3 }">
          <img id="3" src="@/assets/img/profile/3.jpg" @click="selectImg" />
        </div>
        <div v-bind:class="{ 'card-img': true, active: nowImg.userImageUrl == 4 }">
          <img id="4" src="@/assets/img/profile/4.jpg" @click="selectImg" />
        </div>
        <div v-bind:class="{ 'card-img': true, active: nowImg.userImageUrl == 5 }">
          <img id="5" src="@/assets/img/profile/5.jpg" @click="selectImg" />
        </div>
        <div v-bind:class="{ 'card-img': true, active: nowImg.userImageUrl == 6 }">
          <img id="6" src="@/assets/img/profile/6.jpg" @click="selectImg" />
        </div>
      </div>

      <div class="row-btn">
        <button class="btn-get-started" @click="submitProfile">수정하기</button>
      </div>
      <!-- <div
    class="modal fade"
    id="profileModif"
    tabindex="-1"
    aria-labelledby="profileModifLabel"
    aria-hidden="true"
  >
    <div class="modal-dialog modal-lg modal-dialog-scrollable">
      <div class="modal-content">
        <div class="modal-header">
          <h5 class="modal-title" id="profileModifLabel">프로필 수정</h5>
          <button type="button" class="close" data-bs-dismiss="modal" aria-label="Close">
            <span aria-hidden="true"><i class="fa fa-close"></i></span>
          </button>
        </div>
        <div class="modal-body">
          <div class="card2">
            <div class="card2-body">
              <h1 class="ms-3">프로필 사진을 선택해보세요</h1>
              <div class="row mb-3">
                <div v-bind:class="{ 'card-img': true, active: nowImg.userImageUrl == 1 }">
                  <img id="1" src="@/assets/img/profile/1.jpg" @click="selectImg" />
                </div>
                <div v-bind:class="{ 'card-img': true, active: nowImg.userImageUrl == 2 }">
                  <img id="2" src="@/assets/img/profile/2.jpg" @click="selectImg" />
                </div>
                <div v-bind:class="{ 'card-img': true, active: nowImg.userImageUrl == 3 }">
                  <img id="3" src="@/assets/img/profile/3.jpg" @click="selectImg" />
                </div>
                <div v-bind:class="{ 'card-img': true, active: nowImg.userImageUrl == 4 }">
                  <img id="4" src="@/assets/img/profile/4.jpg" @click="selectImg" />
                </div>
                <div v-bind:class="{ 'card-img': true, active: nowImg.userImageUrl == 5 }">
                  <img id="5" src="@/assets/img/profile/5.jpg" @click="selectImg" />
                </div>
                <div v-bind:class="{ 'card-img': true, active: nowImg.userImageUrl == 6 }">
                  <img id="6" src="@/assets/img/profile/6.jpg" @click="selectImg" />
                </div>
              </div>

              <div class="row-btn">
                <button class="btn-get-started" @click="submitProfile">수정하기</button>
              </div>
            </div>
          </div>
        </div>
      </div> -->
    </div>
  </div>
</template>

<style>
.card-img {
  height: 120px;
  width: 120px;
  background-color: rgba(255, 255, 255, 0.06);
  -webkit-backdrop-filter: blur(20px);
  backdrop-filter: blur(20px);
  border-radius: 50%;
  margin: 30px auto 20px auto;
}
.card-img img {
  height: 86%;
  border-radius: 50%;
  margin-left: 7%;
  margin-top: 7%;
}

.card2 .active img {
  box-shadow:
    0 14px 28px rgba(0, 0, 0, 0.25),
    0 10px 10px rgba(0, 0, 0, 0.22);
  transform: translateY(-10px);
}
.card-img img:hover {
  box-shadow:
    0 14px 28px rgba(0, 0, 0, 0.25),
    0 10px 10px rgba(0, 0, 0, 0.22);
  transform: translateY(-10px);
}

.card2 .row {
  display: flex;
  align-items: center;
}

.row-btn {
  display: flex;
  flex-direction: row;
  justify-content: center;
}

.btn-get-deleted {
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
  background: rgb(204, 5, 5);
}
</style>
