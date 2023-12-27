<script setup>
import { ref } from 'vue'
import { logout } from '@/api/user'
const loginUser = ref('')
loginUser.value = localStorage.getItem('loginUser')

const nowUser = ref({})

nowUser.value = JSON.parse(localStorage.getItem('loginUser'))

const onLogout = () => {
  console.log('onLogout 실행됨')
  logout(
    () => {
      localStorage.removeItem('loginUser')
      window.location.href = '/'
    },
    (error) => {
      console.log('error....................', error)
    }
  )
}
</script>

<template>
  <header id="header" class="d-flex align-items-center">
    <div class="container d-flex align-items-center">
      <div class="logo me-auto">
        <router-link to="/"
          ><img id="logoTitle" class="logo" src="@/assets/img/logo2.png"
        /></router-link>
      </div>
      <nav id="navbar" class="navbar d-flex">
        <div id="navbarul" class="align-self-center">
          <div>
            <router-link to="/plan/create"
              ><a class="nav-link scrollto active">여행 계획 짜기</a></router-link
            >
          </div>
          <div>
            <router-link to="/plan/list"
              ><a class="nav-link scrollto active">나의 여행기</a></router-link
            >
          </div>
          <div>
            <router-link to="/articles"
              ><a class="nav-link scrollto active">여행 후기</a></router-link
            >
          </div>
          <div>
            <router-link to="/weather"
              ><a class="nav-link scrollto active">여행지 날씨</a></router-link
            >
          </div>
          <div class="guestMenu" v-if="loginUser == null">
            <div id="guestnav">
              <div id="loginnav">
                <a class="nav-link scrollto" data-bs-toggle="modal" data-bs-target="#loginModal"
                  >로그인</a
                >
              </div>
              <div id="joinnav">
                <a class="nav-link scrollto" data-bs-toggle="modal" data-bs-target="#registerModal"
                  >회원가입</a
                >
              </div>
            </div>
          </div>

          <div id="profilenav" v-else>
            <i class="fas fa-user me-2"></i
            ><img
              class="profileImage ms-3 mt-4 mb-4 me-2"
              src="http://localhost:9090/img/70a18468-a04f-4d31-a08a-3aab06ae6d88profile_img_sample.jpg"
            /><span id="userNickname" class="profile dropdown-toggle" data-bs-toggle="dropdown">
              {{ nowUser.nickname }}
            </span>
            <div class="dropdown">
              <ul class="dropdown-menu">
                <li>
                  <a class="dropdown-item"
                    ><router-link to="/user/detail"
                      ><li>
                        <i class="me-2 fa fa-address-card-o" aria-hidden="true"></i>마이페이지
                      </li></router-link
                    ></a
                  >
                </li>
                <li>
                  <a class="dropdown-item"
                    ><router-link to="/user/modify"
                      ><li>
                        <i class="me-2 fa fa-pencil-square-o" aria-hidden="true"></i>회원정보 수정
                      </li></router-link
                    ></a
                  >
                </li>
                <li>
                  <a class="dropdown-item">
                    <li id="logoutItem" @click="onLogout">
                      <i class="me-2 fa fa-sign-out" aria-hidden="true"></i>로그아웃
                    </li>
                  </a>
                </li>
              </ul>
            </div>
          </div>
        </div>
      </nav>
    </div>
  </header>
</template>

<style scoped>
a {
  text-decoration: none;
}

#userNickname {
  font-size: 18px;
}

.profileImage {
  width: 30px;
  height: 30px;
  border-radius: 15px;
}

.logo {
  display: flex;
  float: left;
  width: 100px;
  height: 100px;
  margin: 10px;
}

header {
  padding: 10px 0px;
  box-shadow: 0 10px 10px -5px rgba(0, 0, 0, 0.07);
  z-index: 1;
  background: var(--color-white);
}

#navbarul div {
  float: left;
  margin-left: 30px;
}

#navbarul {
  margin: 0;
  display: flex;
  align-items: center;
}

.nav-link:hover,
.profile:hover {
  color: var(--color-blue);
  font-weight: bold;
  cursor: pointer;
}
nav a.router-link-exact-active,
nav .menus.router-link-exact-active {
  color: var(--color-blue);
}

*,
:before,
:after {
  box-sizing: border-box;
  transition: 0.3s;
  z-index: 1;
}

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

h1 {
  width: fit-content;
  text-transform: uppercase;
  font-weight: 700;
  font-size: 30px;
  border-left: 10px solid var(--color-blue);
  padding-left: 10px;
  margin: 30px 0px;
}

.menu {
  background: rgba(255, 255, 255, 0.733);
  height: 30px;
  width: 100px;
  border-radius: 50%;
  font-size: 15px;
  cursor: pointer;
}

ul {
  background: white;
  padding: 0.5em 0.5em;
  margin: 0;
  margin-top: 1em;
  border-radius: 5px;
  box-shadow: 0 2px 8px rgba(0, 0, 0, 0.2);
  position: absolute;
  top: 100%;
  white-space: nowrap;
  cursor: default;
  text-align: left;
}
ul.hidden {
  margin-top: 0.7em;
  visibility: hidden;
}
ul:before {
  content: '';
  width: 0.75em;
  height: 0.75em;
  background: inherit;
  transform: rotate(45deg);
}

li {
  list-style: none;
  padding: 0.5em 1em;
  margin: 0;
  position: relative;
  cursor: pointer;
  border-radius: 5px;
  font-weight: 500;
}

li:hover {
  background: var(--color-semigray);
}

.profile {
  font-weight: bold;
}
</style>
