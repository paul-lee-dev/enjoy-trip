<script setup>
import { ref, watch } from 'vue'
import { useRouter } from 'vue-router'
import { registArticle, modifyArticle } from '@/api/article'

const router = useRouter()

const nowUser = ref({})

nowUser.value = JSON.parse(localStorage.getItem('loginUser'))

const props = defineProps({ type: String })

const article = ref({
  articleId: 0,
  subject: '',
  content: '',
  userId: '',
  userNickname: '',
  hit: 0,
  createdTime: ''
})

if (props.type === 'modify') {
  console.log(article.value.articleId + '번글 수정하자!!', article.value)
  modifyArticle(
    article.value,
    (response) => {
      let msg = '글수정 처리시 문제 발생했습니다.'
      if (response.status == 200) msg = '글정보 수정이 완료되었습니다.'
      alert(msg)
      moveList()
    },
    (error) => console.log(error)
  )
}

const subjectErrMsg = ref('')
const contentErrMsg = ref('')
watch(
  () => article.value.subject,
  (value) => {
    let len = value.length
    if (len == 0 || len > 30) {
      subjectErrMsg.value = '제목을 확인해 주세요!!!'
    } else subjectErrMsg.value = ''
  },
  { immediate: true }
)
watch(
  () => article.value.content,
  (value) => {
    let len = value.length
    if (len == 0) {
      contentErrMsg.value = '내용을 확인해 주세요!!!'
    } else contentErrMsg.value = ''
  },
  { immediate: true }
)

function onSubmit() {
  // event.preventDefault();

  if (subjectErrMsg.value) {
    alert(subjectErrMsg.value)
  } else if (contentErrMsg.value) {
    alert(contentErrMsg.value)
  } else {
    props.type === 'regist' ? writeArticle() : updateArticle()
  }
}

function writeArticle() {
  console.log('글등록하자!!', article.value)
  article.value.userId = nowUser.value.userId
  registArticle(
    article.value,
    (response) => {
      let msg = ''
      if (response.status == 201) msg = '글등록이 완료되었습니다.'
      alert(msg)
      moveList()
    },
    (error) => console.log(error)
  )
}

function updateArticle() {
  console.log(article.value.articleId + '번글 수정하자!!', article.value)
  modifyArticle(
    article.value,
    (response) => {
      let msg = '글수정 처리시 문제 발생했습니다.'
      if (response.status == 200) msg = '글정보 수정이 완료되었습니다.'
      alert(msg)
      moveList()
    },
    (error) => console.log(error)
  )
}

function moveList() {
  router.push({ name: 'article-list' })
}
</script>

<template>
  <form @submit.prevent="onSubmit">
    <div class="container">
      <div class="articleContainer">
        <div class="articleTitle mb-3">
          <input
            id="title"
            class="form-control"
            placeholder=" 리뷰 제목을 입력해주세요 "
            v-model="article.subject"
          />
          <p></p>
          <input v-model="article.userId" style="visibility: hidden" />
        </div>
        <p></p>
        <div class="mb-3">
          <froala
            id="edit"
            :tag="'textarea'"
            :config="config"
            v-model:value="article.content"
          ></froala>
        </div>
      </div>
    </div>
    <div style="margin-top: 20px; display: flex; justify-content: center">
      <button
        type="submit"
        class="btn-get-started"
        style="text-align: center"
        v-if="type === 'regist'"
      >
        후기 작성
      </button>
      <button type="submit" class="btn-get-started" style="text-align: center" v-else>
        후기 수정
      </button>
      <button type="button" class="btn-get-started" style="text-align: center" @click="moveList">
        목록
      </button>
    </div>
  </form>
</template>

<style scoped></style>
