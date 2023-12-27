<script setup>
import { ref, onMounted, watch } from 'vue'
import { getSpotList, getSido, getGugun } from '@/api/spot.js'
import VkakaoMap from '@/components/common/VkakaoMap.vue'
import Vselect from '@/components/common/Vselect.vue'
const sidoList = ref([])
const gugunList = ref([{ text: '구군 선택', value: '' }])
const spots = ref([])
const selectSpot = ref({})

const selectedVal = ref()

watch(selectedVal, () => {
  onChangeContentType(selectedVal.value)
})

// const param = ref({
//   searchCondition: {
//     sidoCode: 1,
//     gugunCode: 1,
//     contentTypeId: 12
//   },

//   pagingInfo: {
//     offset: 0,
//     count: 10
//   }
// })

const param = ref({
  sidoCode: 1,
  gugunCode: 1,
  contentId: 12
})

onMounted(() => {
  getSidoList()
})

const getSidoList = () => {
  getSido(
    ({ data }) => {
      let options = []
      console.log('getSidoList.data.............................................', data)
      options.push({ text: '시도 선택', value: '' })
      data.body.forEach((sido) => {
        options.push({ text: sido.sidoName, value: sido.sidoCode })
      })
      sidoList.value = options
      console.log(sidoList.value)
    },
    (err) => {
      console.log(err)
    }
  )
}

const onChangeSido = (val) => {
  param.value.sidoCode = val
  console.log('onChangeSido........', param.value)
  getGugun(
    val,
    ({ data }) => {
      let options = []
      options.push({ text: '구군 선택', value: '' })
      data.body.forEach((gugun) => {
        options.push({ text: gugun.gugunName, value: gugun.gugunCode })
      })
      gugunList.value = options
      console.log('gugunList..............: ', gugunList.value)
    },
    (err) => {
      console.log(err)
    }
  )
}

const onChangeGugun = (val) => {
  console.log('val........', val)
  param.value.gugunCode = val
  console.log('onChangegugun........', param.value)
}

const onChangeContentType = (val) => {
  param.value.contentId = selectedVal.value
  console.log('val........', val)
  console.log('onChangeContnt.....................', param.value)
  // param.value.zscode = val
  getSpots()
}

const getSpots = () => {
  console.log('getSpots..............:', param.value)
  getSpotList(
    // param.value.searchCondition,
    {
      contentId: param.value.contentId,
      sidoCode: param.value.sidoCode,
      gugunCode: param.value.gugunCode
    },
    ({ data }) => {
      // console.log('getSpotsdata..................', data)
      spots.value = data
      // console.log(spots.value)
    },
    (err) => {
      console.log(err)
    }
  )
}

const viewSpot = (spot) => {
  selectSpot.value = spot
}
</script>

<template>
  <div class="row mt-5" style="border: 1px; display: flex">
    <div style="width: 100%; text-align: center">
      <h4 style="font-weight: bold">관광지 정보📝</h4>
    </div>
    <div class="d-flex flex-row mb-2 justify-content-start">
     
        <Vselect :selectOption="sidoList" @onKeySelect="onChangeSido" />
      
      
        <Vselect :selectOption="gugunList" @onKeySelect="onChangeGugun" />
      
      
        <select
          v-model="selectedVal"
          id="search-content-id"
          class="form-select me-2"
          placeholder="관광지 유형"
        >
          <option value="0" selected>관광지 유형</option>
          <option value="12">관광지</option>
          <option value="14">문화시설</option>
          <option value="15">축제공연행사</option>
          <option value="25">여행코스</option>
          <option value="28">레포츠</option>
          <option value="32">숙박</option>
          <option value="38">쇼핑</option>
          <option value="39">음식점</option>
        </select>
      
    </div>
    <VkakaoMap :spots="spots" :selectSpot="selectSpot" />
  </div>
</template>

<style scoped>
#my-list {
  list-style: none;
  margin: auto;
  width: 80%;
  padding: 0;
}

.article-desc {
  margin-top: 0.5rem !important;
  max-height: 150px;
  overflow: hidden;
}

.desc-info-btn {
  display: flex;
  flex-direction: column;
  margin-top: 30px;
}
</style>
