<script setup>
import { ref, onMounted } from 'vue'
import { useRoute } from 'vue-router'
import { detailArticle, deleteArticle, registComment } from '@/api/article.js'

const route = useRoute()
const nowUser = ref({})

nowUser.value = JSON.parse(localStorage.getItem('loginUser'))
// const articleno = ref(route.params.articleno);
const { articleId } = route.params

const commentInfo = ref({
  articleId: articleId,
  userId: 1,
  content: ''
})

const article = ref({})
const comments = ref({})
const commentCnt = ref()

onMounted(() => {
  getArticle()
})

const getArticle = () => {
  // const { articleno } = route.params;
  console.log(articleId + '번글 얻으러 가자!!!')
  detailArticle(
    articleId,
    ({ data }) => {
      article.value = data.body.article
      comments.value = data.body.comments
      commentCnt.value = comments.value.length
      console.log(data)
      console.log('article...............', article.value)
      console.log('comment...............', comments.value)
    },
    (error) => {
      console.log(error)
    }
  )
}

function writeComment() {
  console.log('댓글등록하자!!', comments.value)
  commentInfo.value.userId = nowUser.value.userId
  registComment(
    commentInfo.value,
    (response) => {
      let msg = ''
      if (response.status == 200) msg = '댓글등록이 완료되었습니다.'
      alert(msg)
      window.location.reload()
    },
    (error) => console.log(error)
  )
}
</script>

<template>
  <div class="container">
    <div class="row justify-content-center">
      <div class="col-lg-10">
        <h2 class="my-3 py-3 shadow-sm bg-light text-center">
          <mark class="sky">글보기</mark>
        </h2>
      </div>
      <div class="col-lg-10 text-start">
        <div class="row my-2">
          <h2 class="text-secondary px-5">{{ article.articleId }}. {{ article.subject }}</h2>
        </div>
        <div class="row">
          <div class="col-md-8">
            <div class="clearfix align-content-center">
              <img
                class="avatar me-2 float-md-start bg-light p-2"
                src="https://raw.githubusercontent.com/twbs/icons/main/icons/person-fill.svg"
              />
              <p>
                <span class="fw-bold">{{ article.userNickname }}</span> <br />
                <span class="text-secondary fw-light">
                  {{ article.updatedAt }} 조회 : {{ article.hit }}
                </span>
              </p>
            </div>
          </div>
          <div class="col-md-4 align-self-center text-end">댓글 : {{ commentCnt }}</div>
          <div class="divider mb-3"></div>
          <froalaView v-model:value="article.content"></froalaView>

          <div class="divider mt-3 mb-3"></div>
        </div>
      </div>
    </div>
  </div>

  <div class="container">
    <div class="be-comment-block">
      <h1 class="comments-title" id="comments-title">Comments ({{ commentCnt }})</h1>

      <!-- comment get start  -->
      <div class="be-comment" v-for="ment in comments" :key="ment.id">
        <div class="be-img-comment">
          <a href="blog-detail-2.html">
            <img class="be-ava-comment" src="@/assets/img/profile/1.jpg" />
          </a>
        </div>
        <div class="be-comment-content">
          <span class="be-comment-name">@{{ ment.userNickname }} </span>
          <span class="be-comment-time">
            <i class="fa fa-clock-o"></i>
            {{ ment.writeDate }}
            <button
              v-show="ment.userNickname == nowUser.nickname"
              type="button"
              class="comment-del"
              @click="deleteComment(ment.commentId)"
            >
              삭제 <i class="fas fa-trash-alt"></i>
            </button>
          </span>

          <p class="be-comment-text">
            {{ ment.content }}
          </p>
        </div>
      </div>
      <!-- comment get end  -->
      <form class="form-block" @submit.prevent="writeComment">
        <div class="row comment-up">
          <div class="col-xs-12">
            <div class="form-group">
              <textarea
                class="form-input"
                required=""
                placeholder="댓글을 입력해보세요"
                v-model="commentInfo.content"
              ></textarea>
            </div>
          </div>
          <button type="button" class="btn btn-get-started pull-right" @click="writeComment">
            댓글 등록하기
          </button>
        </div>
      </form>
    </div>
  </div>
  <div class="articleBtns">
    <router-link to="/article/list"
      ><button class="btn-get-started">다른 여행기도 궁금하신가요 ?</button></router-link
    >
    <button
      v-show="article.userNickname == nowUser.nickname"
      class="deleteArticle"
      id="deleteBtn"
      @click="deleteArticle"
    >
      삭제
    </button>
  </div>
</template>

<style>
.todo2 {
  width: 95%;
  margin: auto;
  background-color: var(--color-white);
  padding: 2em;
  box-shadow: 1px 2px 4px #0003;
}

.todo2 h1 {
  margin: 10px;
}

.todo2 ul {
  -webkit-padding-start: 1.5em;
  padding-inline-start: 1.5em;
  font-weight: 400;
  text-align: left;
}
.todo2 ul ::marker {
  content: '>';
  color: #48b;
}
.todo2 ul ul ::marker {
  content: '>>';
}

.todo2 ul li {
  font-weight: 600;
}

.todo2 li {
  position: relative;
  -webkit-padding-start: 0.5em;
  padding-inline-start: 0.5em;
  padding-top: 1.5em;
  border-bottom: 1px solid #48b2;
  cursor: default;
  transition: color 0.4s;
}
.todo2 li::after {
  content: attr(theLittleDetails);
  position: absolute;
  left: 15%;
  bottom: calc(100% - 1.5em);
  background-image: conic-gradient(from 10deg at 1em 100%, #fff 30deg, #fff0 30deg),
    linear-gradient(0deg, #fff0 1.5em, #fff 1.5em);
  color: #246;
  font-weight: 700;
  padding: 1em 2em 2.5em;
  pointer-events: none;
  opacity: 0;
  filter: drop-shadow(1px 2px 4px #0003);
  transition: opacity 0.4s;
}
.todo2 li:hover {
  color: #48b;
}
.todo2 li:hover::marker {
  font-weight: 700;
}
.todo2 li:hover::after {
  opacity: 1;
  transition-delay: 0.2s;
}

.todo2-info {
  text-align: left;
  margin: 30px 30px 15px;
}

.todo2-tot {
  display: flex;
  justify-content: space-between;
  align-items: center;
}

.todo2-btn {
  display: flex;
  justify-content: flex-start;
  align-items: center;
}
.articleBtns {
  display: flex;
  align-items: center;
  justify-content: center;
  flex-direction: row;
  margin-top: 20px;
}

.scrapBtn i,
.scrapBtn {
  color: var(--color-blue);
  font-weight: 600;
  margin-right: 6px;
}

.fa-comment {
  font-size: 20px;
  color: var(--color-blue);
}

#my-list .btn-search {
  margin-top: 10px;
}

.be-comment-block {
  margin-bottom: 50px !important;
  border-radius: 2px;
  padding: 50px 20px;
}

.comments-title {
  font-size: 25px;
  color: #262626;
  margin-bottom: 40px;
}

.be-img-comment {
  width: 60px;
  height: 60px;
  float: left;
  margin-bottom: 15px;
}

.be-ava-comment {
  width: 60px;
  height: 60px;
  border-radius: 50%;
}

.be-comment-content {
  margin-left: 80px;
}

.be-comment-content span {
  display: inline-block;
  width: 49%;
  margin-bottom: 15px;
}

.be-comment-name {
  font-size: 15px;
  font-weight: 700;
  text-align: left;
}

.be-comment-content a {
  color: #383b43;
}

.be-comment-content span {
  display: inline-block;
  width: 49%;
  margin-bottom: 15px;
}

.be-comment-time {
  text-align: right;
}

.be-comment-time {
  font-size: 13px;
  color: #b4b7c1;
}

.be-comment-text {
  font-size: 15px;
  /* line-height: 18px; */
  color: var(--color-black);
  display: block;
  background: var(--color-white);
  padding: 25px 20px;
  border-radius: 10px;
  text-align: left;
}

.form-group.fl_icon .icon {
  top: 1px;
  left: 16px;
  width: 48px;
  height: 48px;
  background: #f6f6f7;
  color: #b5b8c2;
  text-align: center;
  line-height: 50px;
  -webkit-border-top-left-radius: 2px;
  -webkit-border-bottom-left-radius: 2px;
  -moz-border-radius-topleft: 2px;
  -moz-border-radius-bottomleft: 2px;
  border-top-left-radius: 2px;
  border-bottom-left-radius: 2px;
}

.form-group .form-input {
  font-size: 16px;
  font-weight: 400;
  color: var(--color-black);
  width: 100%;
  height: 50px;
  padding: 20px;
  border-radius: 10px;
  margin-bottom: 30px;
  resize: none;
}

.form-group.fl_icon .form-input {
  padding-left: 70px;
}

.form-group textarea.form-input {
  height: 150px;
  margin-top: 30px;
}

.pull-right {
  width: max-content;
}

.comment-up {
  justify-content: flex-end !important;
}

.comment-del,
.comment-del i {
  color: rgb(187, 4, 4);
  font-weight: 600;
  border: none;
  background: var(--color-background);
}
</style>
