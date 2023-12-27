<script setup>
import { ref } from 'vue'
import { modifyUser, deleteUser } from '@/api/user'
import { useRouter } from 'vue-router'

const router = useRouter()

const nowUser = ref({})

nowUser.value = JSON.parse(localStorage.getItem('loginUser'))

const modifyInfo = () => {
  console.log(nowUser.value)
  let reqUser = nowUser.value
  modifyUser(
    {
      userId: reqUser.userId,
      email: reqUser.email,
      nickname: reqUser.nickname,
      phoneNumber: reqUser.phoneNumber
    },
    () => {
      console.log('회원정보 수정 성공')
      localStorage.setItem('loginUser', JSON.stringify(nowUser.value))
    },
    (error) => {
      console.log('회원정보 수정 실패................error: ', error)
    }
  )
}

const deleteAction = () => {
  deleteUser(
    nowUser.value.userId,
    () => {
      console.log('회원탈퇴 성공')
      localStorage.removeItem('loginUser')
      router.push('/')
    },
    (error) => {
      console.log('회원탈퇴 실패...........error', error)
    }
  )
}
</script>

<template>
  <div class="card2">
    <div class="card2-body">
      <div class="row mb-3">
        <div class="col-sm-3">
          <h6 class="mb-0">닉네임</h6>
        </div>
        <div class="col-sm-9 text-secondary">
          <input
            type="text"
            class="form-control"
            id="nickname"
            placeholder="사용자 명을 입력해주세요"
            name="nickname"
            v-model="nowUser.nickname"
          />
          <p class="ms-2 mt-sm-1" id="message-nickname"></p>
        </div>
      </div>
      <div class="row mb-3">
        <div class="col-sm-3">
          <h6 class="mb-0">이메일</h6>
        </div>
        <div class="col-sm-9 text-secondary">
          <input type="text" class="form-control" id="email" v-model="nowUser.email" />
        </div>
      </div>
      <div class="row mb-3">
        <div class="col-sm-3">
          <h6 class="mb-0">전화번호</h6>
        </div>
        <div class="col-sm-9 text-secondary">
          <input type="text" class="form-control" id="phoneNumber" v-model="nowUser.phoneNumber" />
        </div>
      </div>
      <div class="row-btn">
        <button class="btn-get-started" @click="modifyInfo">수정하기</button>
        <button class="btn-get-deleted" @click="deleteAction">회원탈퇴</button>
      </div>
    </div>
  </div>
  <!-- <div
    class="modal fade"
    id="infoModif"
    tabindex="-1"
    aria-labelledby="infoModifLabel"
    aria-hidden="true"
  >
    <div class="modal-dialog modal-lg modal-dialog-scrollable">
      <div class="modal-content">
        <div class="modal-header">
          <h5 class="modal-title" id="infoModifLabel">회원정보수정</h5>
          <button type="button" class="close" data-bs-dismiss="modal" aria-label="Close">
            <span aria-hidden="true"><i class="fa fa-close"></i></span>
          </button>
        </div>
        <div class="modal-body">
          <div class="card2">
            <div class="card2-body">
              <div class="row mb-3">
                <div class="col-sm-3">
                  <h6 class="mb-0">이름(닉네임)</h6>
                </div>
                <div class="col-sm-9 text-secondary">
                  <input
                    type="text"
                    class="form-control"
                    id="nickname"
                    placeholder="사용자 명을 입력해주세요"
                    name="nickname"
                    @keyup="nicknameCheck"
                  />
                  <p class="ms-2 mt-sm-1" id="message-nickname"></p>
                </div>
              </div>
              <div class="row mb-3">
                <div class="col-sm-3">
                  <h6 class="mb-0">이메일</h6>
                </div>
                <div class="col-sm-9 text-secondary">
                  <input
                    type="text"
                    readonly
                    class="form-control"
                    id="email"
                    v-model="nowUser.email"
                  />
                </div>
              </div>
              <div class="row mb-3">
                <div class="col-sm-3">
                  <h6 class="mb-0">전화번호</h6>
                </div>
                <div class="col-sm-9 text-secondary">
                  <input
                    type="text"
                    readonly
                    class="form-control"
                    id="phoneNumber"
                    v-model="nowUser.phoneNumber"
                  />
                </div>
              </div>
              <div class="row-btn">
                <button class="btn-get-started" @click="modifInfo">수정하기</button>
                <button class="btn-get-deleted" @click="deleteInfo">회원탈퇴</button>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div> -->
</template>

<style>
.card2 .row {
  display: flex;
  align-items: baseline;
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

#infoModif #message-nickname {
  text-align: left;
}
</style>
