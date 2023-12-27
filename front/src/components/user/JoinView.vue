<script setup>
// import { passwordCheck, resetForm, emailCheck, nicknameCheck } from '@/api/user/registerCheck.js'
// import { join } from '@/api/user/regist.js'
// export default {
//   methods: {
//     passwordCheck: passwordCheck,
//     resetForm: resetForm,
//     emailCheck: emailCheck,
//     join: join,
//     nicknameCheck: nicknameCheck
//   }
// }

import { ref } from 'vue'
import { uploadFile } from '@/api/file'
import { registUser } from '@/api/user'

const profileImg = ref(null)
const profileImgUrl = ref('')

const newUser = ref({})

const joinAction = () => {
  registUser(
    newUser.value,
    () => {
      console.log('회원가입 성공')
      window.location.href = '/'
    },
    (error) => {
      console.log('회원가입 실패..............error = ', error)
    }
  )
}

// const onChangeFile = () => {
//   const inputImg = document.querySelector('#profileImgInput').files
//   console.log(inputImg);
//   profileImg.value = inputImg;
//   uploadProfile
// }

// const uploadProfileImg = () => {
//   let imgData = new FormData()
//   imgData.append('file', profileImg)
//   uploadFile(
//     imgData,
//     ({ data }) => {
//       profileImgUrl.value = data
//       console.log(data)
//     },
//     (error) => {
//       console.log('error!...................', error)
//     }
//   )
// }
</script>

<template>
  <!-- ===== Register modal start ===== -->
  <div
    class="modal fade"
    id="registerModal"
    data-bs-backdrop="static"
    data-bs-keyboard="false"
    tabindex="-1"
    aria-labelledby="staticBackdropLabel"
    aria-hidden="true"
  >
    <div class="modal-dialog modal-lg modal-dialog-centered">
      <div class="modal-content">
        <form action="${root}/join" method="post" id="register">
          <!-- enjoytrip -->
          <input type="hidden" id="action" name="action" value="join" />
          <div class="modal-header">
            <h4 class="modal-title">
              <label class="fw-bold border-bottom border-primary border-5"> 회원가입</label>
            </h4>
            <button type="button" class="btn-close" data-bs-dismiss="modal"></button>
          </div>

          <div class="modal-body joinbody">
            <div class="mb-4 ms-2 me-2">
              <img
                class="profileImage ms-3 mt-3 mb-4"
                src="http://localhost:9090/img/70a18468-a04f-4d31-a08a-3aab06ae6d88profile_img_sample.jpg"
              />
              <form
                enctype="multipart/form-data"
                method="POST"
                action="http://localhost:9090/files"
              >
                <input
                  type="file"
                  class="form-control"
                  id="profileImgInput"
                  name="file"
                  aria-describedby="inputGroupFileAddon04"
                  aria-label="Upload"
                />
                <button class="btn btn-outline-secondary" id="inputGroupFileAddon04">Upload</button>
              </form>
            </div>
            <div class="mb-4 ms-2 me-2">
              <label for="name" class="form-label">이름:</label>
              <input
                type="text"
                class="form-control"
                id="nickname"
                placeholder="사용자 명을 입력해주세요"
                name="nickname"
                v-model="newUser.nickname"
                required
              />
              <p class="ms-2 mt-sm-1" id="message-nickname"></p>
            </div>
            <div class="mb-4 ms-2 me-2">
              <label for="email" class="form-label">이메일:</label>
              <input
                type="text"
                class="form-control"
                id="email"
                placeholder="이메일을 입력해주세요"
                name="email"
                v-model="newUser.email"
                required
              />
              <p class="ms-2 mt-sm-1" id="message-email"></p>
            </div>
            <div class="mb-4 ms-2 me-2">
              <label for="password" class="form-label">비밀번호:</label>
              <input
                type="password"
                class="form-control"
                id="password"
                placeholder="비밀번호를 입력해주세요"
                name="password"
                v-model="newUser.password"
                required
              />
            </div>
            <div class="mb-4 ms-2 me-2">
              <label for="confirm_password" class="form-label">비밀번호 확인:</label>
              <input
                type="password"
                class="form-control"
                id="confirm_password"
                placeholder="비밀번호를 한번 더 입력해주세요"
                name="confirm_password"
                v-model="newUser.passwordCheck"
                required
              />
              <p class="ms-2 mt-sm-1" id="message"></p>
            </div>
            <div class="mb-4 ms-2 me-2">
              <label for="phoneNumber" class="form-label">전화번호:</label>
              <input
                type="text"
                class="form-control"
                name="phone_number"
                id="phoneNumber"
                placeholder="전화번호를 입력해주세요"
                v-model="newUser.phoneNumber"
                required
              />
            </div>
          </div>

          <div class="modal-footer">
            <button
              type="button"
              id="btn-register"
              class="btn btn-outline-primary btn-sm"
              @click="joinAction"
            >
              회원가입
            </button>
            <button
              type="button"
              id="btn-reset"
              class="btn btn-outline-success btn-sm"
              @click="resetForm"
            >
              초기화
            </button>
            <button type="button" class="btn btn-outline-dark btn-sm" data-bs-dismiss="modal">
              닫기
            </button>
          </div>
        </form>
      </div>
    </div>
  </div>
  <!-- Register modal end -->
</template>

<style scoped>
.joinbody {
  text-align: left;
}

.profileImage {
  width: 140px;
  height: 140px;
  border-radius: 25px;
}
</style>
