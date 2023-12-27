<template>
  <div class="hello">
    <MyPageInfo></MyPageInfo>
    <div class="mylist row mt-5">
      <div class="col-md-5 leftBox">
        <h1>Like<i class="ms-2 far fa-thumbs-up"></i></h1>
        <LikeArticleList></LikeArticleList>
      </div>
      <div class="col-md-5 rightBox">
        <h1>BookMark<i class="ms-2 far fa-bookmark"></i></h1>
        <AttrBookmarkList></AttrBookmarkList>
      </div>
    </div>

    <div>
      <router-link to="/"
        ><button class="btn-get-started color-3">메인으로 돌아가기</button></router-link
      >
    </div>
  </div>
</template>

<style scoped>
.mylist {
  margin: 40px 0px;
  padding-bottom: 30px;
}

.mylist h1 {
  margin-left: 30px;
}

.mylist .leftBox,
.mylist .rightBox {
  background-color: rgba(255, 255, 255, 0.46);

  backdrop-filter: blur(20px);
  border-radius: 8px;
  box-shadow: 5px 5px 40px rgba(0, 0, 0, 0.1);
  height: 600px;
}
</style>
