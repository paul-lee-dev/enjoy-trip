<template>
  <div id="box">
    <img
      v-if="!isImg"
      class="img"
      src="https://img.freepik.com/free-psd/travel-background-composition-with-backpack_23-2149603154.jpg?w=1380&t=st=1684904625~exp=1684905225~hmac=d5a23928d33003ee6f767f2056d9cd7246fb29951045388d35f0212c3ce100ca"
      alt=""
    />
    <img v-else-if="isImg" class="img" :src="imgList[0]" alt="" />
    <!--"../assets/img/korea-geab21995b_1920.jpg"-->
    <div class="heading">
      <h6><i class="fa fa-map-marker" aria-hidden="true"></i>&nbsp;GaVoyage</h6>
      <h4 class="mt-3">{{ review.title }}</h4>
      <div id="likeAndBookmark">
        <Like :reviewInit="{ isLiked: review.isLiked, id: review.reviewIdx }"></Like>
      </div>
    </div>

    <div class="data">
      <span class="writeDate">{{ review.createdAt }}</span>
      <span class="user-id">{{ review.writerName }} 님의 여행</span>
    </div>
    <p class="texts">더욱 자세한 후기를 보고싶다면, 클릭해주세요</p>
    <!-- {{ review.contents.slice(0, 30) }} -->
  </div>
</template>
<script>

</script>
<style scoped>
#box {
  width: 270px;
  height: 390px;
  border-radius: 8px;
  overflow: hidden;
  margin: 20px 15px 80px;
  transition: all 0.3s cubic-bezier(0.42, 0, 0.58, 1);
  background: var(--color-white);
  box-shadow: 2px 2px 10px rgba(0, 0, 0, 0.2);
}

#box:hover {
  box-shadow: 0 14px 28px rgba(0, 0, 0, 0.25), 0 10px 10px rgba(0, 0, 0, 0.22);
  transform: translateY(-10px);
}

#box * {
  text-align: left;
}

#box .img {
  display: block;
  width: 100%;
  padding: 0;
  height: 180px;
}

#box .heading {
  padding: 15px 0px 0px 10px;
  font-size: 28px;
}

#box .data {
  display: flex;
  flex-direction: column;
  padding: 8px 8px 8px 10px;
  font-size: 12px;
  color: #666;
  margin-top: 5px;
}

#box .data span {
  padding: 0;
}

#box .data .date {
  margin-bottom: 1px;
}

#box .data .user-id {
  font-size: 16px;
  color: #000;
  font-weight: 600;
}

#box .texts {
  padding: 15px 0px 0px 10px;
  font-size: 14px;
  line-height: 18px;
}

#likeAndBookmark {
  display: flex;
  justify-content: flex-end;
  margin-top: -87px;
  padding-bottom: 30px;
  align-items: center;
}

h6,
h6 i {
  color: var(--color-semigray);
}

#box h4 {
  font-weight: 700;
}
</style>
