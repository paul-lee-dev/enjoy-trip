<script setup>
import { ref, onMounted } from 'vue'
import { useRouter } from 'vue-router'
import { listArticle } from '@/api/article.js'

import VSelect from '@/components/common/VSelect.vue'
import ArticleListItem from './ArticleListItem.vue'
import VPageNavigation from '@/components/common/VPageNavigation.vue'

const router = useRouter()

const selectOption = ref([
  { text: '검색조건', value: '' },
  { text: '글번호', value: 'article_id' },
  { text: '제목', value: 'subject' },
  { text: '작성자아이디', value: 'user_id' }
])

const articles = ref([])
const currentPage = ref(1)
const totalPage = ref(0)
const { VITE_ARTICLE_LIST_SIZE } = import.meta.env
const param = ref({
  pgno: currentPage.value,
  spp: VITE_ARTICLE_LIST_SIZE,
  key: '',
  word: ''
})

onMounted(() => {
  getArticleList()
})

const changeKey = (val) => {
  console.log('BoarList에서 선택한 조건 : ' + val)
  param.value.key = val
}

const getArticleList = () => {
  console.log('서버에서 글목록 얻어오자!!!', param.value)
  console.log('글목록...............:', param.value)

  listArticle(
    param.value,
    ({ data }) => {
      console.log('data.........:', data)
      data = data.body
      console.log('data.........:', data)
      articles.value = data.articles
      currentPage.value = data.currentPage
      totalPage.value = data.totalPageCount
    },
    (error) => {
      console.log(error)
    }
  )
}

const onPageChange = (val) => {
  console.log(val + '번 페이지로 이동 준비 끝!!!')
  currentPage.value = val
  param.value.pgno = val
  getArticleList()
}

const moveWrite = () => {
  router.push({ name: 'article-write' })
}
</script>

<template>
  <div class="articleListTitle">
    <h1>요즘 인기있는 여행지와 여행기를 만나보아요</h1>

    <div class="col-lg-10">
      <div class="row align-self-center mb-2">
        <div class="col-md-2 text-start">
          <button type="button" class="btn btn-outline-primary btn-sm" @click="moveWrite">
            글쓰기
          </button>
        </div>
        <div class="col-md-5 offset-5">
          <form class="d-flex">
            <VSelect :selectOption="selectOption" @onKeySelect="changeKey" />
            <div class="input-group input-group-sm ms-1">
              <input
                type="text"
                class="form-control"
                v-model="param.word"
                placeholder="검색어..."
              />
              <button class="btn btn-dark" type="button" @click="getArticleList">검색</button>
            </div>
          </form>
        </div>
      </div>
      <table class="table table-hover">
        <thead>
          <tr class="text-center">
            <th scope="col">글번호</th>
            <th scope="col">제목</th>
            <th scope="col">작성자</th>
            <th scope="col">조회수</th>
            <th scope="col">작성일</th>
          </tr>
        </thead>
        <tbody>
          <ArticleListItem
            v-for="article in articles"
            :key="article.articleId"
            :article="article"
          ></ArticleListItem>
        </tbody>
      </table>
    </div>
    <VPageNavigation
      :current-page="currentPage"
      :total-page="totalPage"
      @pageChange="onPageChange"
    ></VPageNavigation>
  </div>
</template>

<style scoped>
.articleListTitle {
  position: relative;
  width: 80%;
  margin: 80px auto 0;
}

.articleContainer {
  display: flex;
  width: 80%;
  margin: auto;
  flex-wrap: wrap;
  flex-direction: row;
  justify-content: flex-start;
}
</style>
