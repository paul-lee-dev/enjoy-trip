<script setup>
import { ref, watch, onMounted } from 'vue'
import marker12 from '@/assets/img/marker/marker12.png'
import marker14 from '@/assets/img/marker/marker14.png'
import marker15 from '@/assets/img/marker/marker15.png'
import marker25 from '@/assets/img/marker/marker25.png'
import marker28 from '@/assets/img/marker/marker28.png'
import marker32 from '@/assets/img/marker/marker32.png'
import marker38 from '@/assets/img/marker/marker38.png'
import marker39 from '@/assets/img/marker/marker39.png'
import noimg from '@/assets/img/marker/noimg.png'

var map
const positions = ref([])
const markers = ref([])
const props = defineProps({ spots: Array, selectSpot: Object })
const markerImg = ref()

watch(
  () => props.selectSpot,
  () => {
    // 이동할 위도 경도 위치를 생성합니다
    var moveLatLon = new kakao.maps.LatLng(props.selectSpot.lat, props.selectSpot.lon)
    // 지도 중심을 부드럽게 이동시킵니다
    // 만약 이동할 거리가 지도 화면보다 크면 부드러운 효과 없이 이동합니다
    map.panTo(moveLatLon)
  },
  { deep: true }
)

onMounted(() => {
  if (window.kakao && window.kakao.maps) {
    initMap()
  } else {
    const script = document.createElement('script')
    script.src = `//dapi.kakao.com/v2/maps/sdk.js?autoload=false&appkey=${
      import.meta.env.VITE_KAKAO_MAP_SERVICE_KEY
    }&libraries=services,clusterer`
    /* global kakao */
    script.onload = () => kakao.maps.load(() => initMap())
    document.head.appendChild(script)
  }
})

watch(
  () => props.spots,
  () => {
    positions.value = []
    props.spots.body.forEach((spot) => {
      let obj = {}
      // console.log(spot)
      obj.latlng = new kakao.maps.LatLng(spot.lat, spot.lon)
      obj.title = spot.title
      obj.contentId = spot.contentId
      obj.image = spot.image
      obj.address = spot.address
      positions.value.push(obj)
    })
    // console.log('positions.......................................:', positions)
    loadMarkers()
  },
  { deep: true }
)

const initMap = () => {
  const container = document.getElementById('map')
  const options = {
    center: new kakao.maps.LatLng(33.450701, 126.570667),
    level: 3
  }
  map = new kakao.maps.Map(container, options)

  // loadMarkers();
}
// ========================== load Markers start ==========================
const loadMarkers = () => {
  console.log('loadMarkers() called')
  // 현재 표시되어있는 marker들이 있다면 map에 등록된 marker를 제거한다.
  deleteMarkers()

  console.log('deleteMarkers() 완료')

  // markerImg.value = marker12

  // const imgSrc = '../assets/img/marker/marker39.png'
  // 마커 이미지의 이미지 크기 입니다
  const imgSize = new kakao.maps.Size(24, 35)
  // const markerImage = new kakao.maps.MarkerImage(markerImg.value, imgSize)

  // 마커를 생성합니다
  markers.value = []
  // console.log('b4 foreach position.length............', length(positions.value))
  console.log('b4 foreach position', positions.value)
  positions.value.forEach((position) => {
    console.log('position......................', position)
    console.log('position.contentId......................', position.contentId)

    if (position.contentId == '12') {
      markerImg.value = marker12
    } else if (position.contentId == '14') {
      markerImg.value = marker14
    } else if (position.contentId == '15') {
      markerImg.value = marker15
    } else if (position.contentId == '25') {
      markerImg.value = marker25
    } else if (position.contentId == '28') {
      markerImg.value = marker28
    } else if (position.contentId == '32') {
      markerImg.value = marker32
    } else if (position.contentId == '38') {
      markerImg.value = marker38
    } else if (position.contentId == '39') {
      markerImg.value = marker39
    } else {
      markerImg.value = noimg
    }
    const infowindow = new kakao.maps.InfoWindow({
      position: position.latlng,
      content: `
                    <div style="width:400px;">
                        <a style="display: block; padding: 6px;background-color: deepskyblue; font-size: 20px; font-weight: bold; color: white;display: block; text-align: center;">${position.title}</a>
                        <div style="display: flex; justify-content: center; align-items: center;">
                            <img src="${position.image}" style="width:200px;">
                            <a style="margin: 20px;">${position.address}</a>
                        </div>
                    </div>
                `
    })

    const markerImage = new kakao.maps.MarkerImage(markerImg.value, imgSize)
    const marker = new kakao.maps.Marker({
      map: map, // 마커를 표시할 지도
      position: position.latlng, // 마커를 표시할 위치
      title: position.title, // 마커의 타이틀, 마커에 마우스를 올리면 타이틀이 표시됨.
      clickable: true, // // 마커를 클릭했을 때 지도의 클릭 이벤트가 발생하지 않도록 설정합니다
      image: markerImage // 마커의 이미지
    })
    markers.value.push(marker)
    kakao.maps.event.addListener(marker, 'mouseover', makeOverListener(map, marker, infowindow))
    kakao.maps.event.addListener(marker, 'mouseout', makeOutListener(infowindow))
  })

  console.log('마커 생성 완료')

  // 4. 지도를 이동시켜주기
  // 배열.reduce( (누적값, 현재값, 인덱스, 요소)=>{ return 결과값}, 초기값);
  const bounds = positions.value.reduce(
    (bounds, position) => bounds.extend(position.latlng),
    new kakao.maps.LatLngBounds()
  )

  console.log('지도 이동 끝')

  map.setBounds(bounds)
}
// ========================== load Markers end ==========================

const deleteMarkers = () => {
  if (markers.value.length > 0) {
    console.log('sadflasdfhdsalfs')
    markers.value.forEach((marker) => marker.setMap(null))
  }
}
function makeOverListener(map, marker, infowindow) {
  return function () {
    infowindow.open(map, marker)
  }
}

// 인포윈도우를 닫는 클로저를 만드는 함수입니다
function makeOutListener(infowindow) {
  return function () {
    infowindow.close()
  }
}
</script>

<template>
  <div id="map"></div>
</template>

<style>
#map {
  width: 100%;
  height: 700px;
}
</style>
