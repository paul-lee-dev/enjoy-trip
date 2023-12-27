<script setup>
import { login } from '@/api/user'
import { ref } from 'vue'

const email = ref('')
const password = ref('')

const onLogin = () => {
  login(
    {
      email: email.value,
      password: password.value
    },
    ({ data }) => {
      console.log('loginUser..................', data)
      localStorage.setItem('loginUser', JSON.stringify(data.body))
      window.location.href = '/'
    },
    (error) => {
      console.log('error.........................', error)
    }
  )
}
</script>

<template>
  <!-- ===== Login modal start ===== -->
  <div
    class="modal fade"
    id="loginModal"
    data-bs-backdrop="static"
    data-bs-keyboard="false"
    tabindex="-1"
    aria-labelledby="staticBackdropLabel"
    aria-hidden="true"
  >
    <div class="modal-dialog modal-sm modal-dialog-centered">
      <div class="modal-content">
        <div class="modal-header">
          <h4 class="modal-title">
            <label class="fw-bold border-bottom border-primary border-5"> 로그인</label>
          </h4>
          <button type="button" class="btn-close" data-bs-dismiss="modal"></button>
        </div>

        <div class="modal-body loginbody">
          <div class="mb-3 ms-2 me-2">
            <label for="loginid" class="form-label">아이디:</label>
            <input
              v-model="email"
              type="text"
              class="form-control"
              id="loginid"
              placeholder="아이디..."
              name="member_id"
            />
          </div>
          <div class="mb-3 ms-2 me-2">
            <label for="loginpw" class="form-label">비밀번호:</label>
            <input
              v-model="password"
              type="password"
              class="form-control"
              id="loginpw"
              placeholder="비밀번호..."
              name="member_password"
              @keyup.enter="onLogin"
            />
          </div>
          <div class="socialLogin">
            <div id="kakao-box">
              <a href="http://localhost:8080/oauth2/authorization/kakao">
                <img id="icon-img" src="@/assets/img/kakao_login_btn.png" />
              </a>
            </div>
            <div id="google-box">
              <a href="http://localhost:8080/oauth2/authorization/google">
                <img id="icon-img" src="@/assets/img/google_login_btn.png" />
              </a>
            </div>
          </div>
        </div>

        <div class="modal-footer">
          <button
            @click="onLogin"
            type="button"
            id="btn-login"
            class="btn btn-outline-primary btn-sm"
          >
            로그인
          </button>
          <button
            type="button"
            id="btn-find-id"
            class="btn btn-outline-success btn-sm"
            data-bs-toggle="modal"
            data-bs-target="#registerModal"
          >
            <router-link to="/user/regist">회원가입</router-link>
          </button>
        </div>
      </div>
    </div>
  </div>
  <!-- Login modal end -->
</template>

<style scoped>
.form-label {
  margin-bottom: 0.5rem;
  font-weight: 700;
}
.socialLogin {
  display: flex;
  margin: auto;
  flex-direction: column;
  align-items: center;
}

.socialLogin a {
  width: 80%;
}

.loginbody {
  text-align: left;
}

#kakao-box img {
  width: 200px;
  height: 44px;
  border-radius: 12px;
  margin-bottom: 8px;
  /* font-size: 15px; */
  /* font-weight: bold; */
  color: #000000cb;
  /* background-color: #fee500; */
  display: flex;
  align-items: center;
  justify-content: center;
  cursor: pointer;
}

#google-box img {
  width: 200px;
  height: 44px;
  /* border-radius: 12px; */
  margin-bottom: 8px;
  font-size: 15px;
  font-weight: bold;
  color: white;
  /* background-color: #03c75a; */
  display: flex;
  align-items: center;
  justify-content: center;
  cursor: pointer;
}

#icon-img {
  margin-right: 2%;
  margin-top: 6px;
  margin-bottom: 6px;
  width: 40px;
}
</style>
