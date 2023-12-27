/* eslint-disable no-undef */
import api from '@/util/axios.js'
import { $ } from '@/util/elementTool.js'

export async function showMap(searchList) {
  try {
    let searchUrl = '/regions/search'

    //   ? sido - code=" +
    // searchList[0] +
    // "&gugun-code=" +
    // searchList[1] +
    // "&content-type-id=" +
    // searchList[2];

    let params = {
      sido_code: searchList[0],
      gugun_code: searchList[1],
      content_type_id: searchList[2]
    }
    const res = await api.post(searchUrl, params)
    // const res = await api(searchUrl);
    const data = await res.data
    makeMap(data)
  } catch (e) {
    alert('검색 결과가 없습니다.')
    console.log(e)
  }
}

function makeMap(data) {
  //console.log("map" , data);
  console.log(data)
  let startX = data[0].latitude
  let startY = data[0].longitude
  console.log(startX, startY)
  let mapContainer = $('#map'), // 지도를 표시할 div
    mapOption = {
      center: new kakao.maps.LatLng(Number(startX), Number(startY)), // 지도의 중심좌표.
      level: 6 // 지도의 확대 레벨
    }

  // 지도를 표시할 div와 지도 옵션으로 지도를 생성합니다
  let mapi = new kakao.maps.Map(mapContainer, mapOption)

  for (let i = 0; i < data.length; i++) {
    //console.log(data[i].first_image);
    let mapX = data[i].latitude
    let mapY = data[i].longitude
    let latlng = new kakao.maps.LatLng(mapX, mapY)
    // 마커를 생성합니다
    let marker = new kakao.maps.Marker({
      map: mapi, // 마커를 표시할 지도
      position: latlng, // 마커를 표시할 위치
      title: data[i].title // 마커의 타이틀, 마커에 마우스를 올리면 타이틀이 표시됩니다
    })

    // 마커에 표시할 인포윈도우를 생성합니다
    let infowindow = new kakao.maps.InfoWindow({
      content:
        '<div style="width:350px; height: 170px">' +
        '<div style="height:20%;" class="row mt-1 ms-2 fw-bold">' +
        data[i].title +
        '</div>' +
        '<div style="height:80%;" class="row m-1 d-flex">' +
        '<div style="width:40%;">' +
        '<img style="width:100%; height:100px;" src=' +
        data[i].first_image +
        ' />' +
        '</div>' +
        '<div style="width:60%;">' +
        '<p style="font-size: 10pt; margin: 1px" class="fw-bold">주소</p>' +
        '<p style="font-size: 10pt; margin: 1px">' +
        data[i].addr1 +
        '\n' +
        data[i].addr2 +
        '</p>' +
        '</div>' +
        '</div>' +
        '</div>' // 인포윈도우에 표시할 내용
    })

    // 마커에 mouseover 이벤트와 mouseout 이벤트를 등록합니다
    // 이벤트 리스너로는 클로저를 만들어 등록합니다
    // for문에서 클로저를 만들어 주지 않으면 마지막 마커에만 이벤트가 등록됩니다
    // kakao.maps.event.addListener(marker, 'mouseover', makeOverListener(mapi, marker, infowindow));
    // kakao.maps.event.addListener(marker, 'mouseout', makeOutListener(infowindow));
    kakao.maps.event.addListener(marker, 'click', showDescription(data[i]))
  }
}

function showDescription(data) {
  return async function () {
    let detailUrl = '/regions/description?contentId=' + data.content_id
    try {
      const res = await api.get(detailUrl)
      const description = await res.data
      let mlist = $('#my-list')
      mlist.replaceChildren()
      let li = document.createElement('li')
      let content = `<div class="m1">
			    					<hr>
				    				<h5 id = "data-title" style="font-weight: bold;">${data.title}</h5>
                    <span id = "data-id" style = "display:none;" >${data.content_id}</span>
				    				<hr/>
				    				<div style="margin: 10px";>
				        				<img class = "dataImg mb-4" src="${
                          data.first_image
                        }" onerror="this.src='https://cdn-icons-png.flaticon.com/512/4944/4944051.png'" />
				        				<br/>
				        				<span style="font-size: 14px; font-weight:bold;">주소 : ${data.addr1}</span>
				        				<br/>
				        				<div class = "mt-2 review-desc" style="font-size: 14px;">${description.overview.substr(
                          0,
                          201
                        )}</div>
                        
				    				</div>
                    <button class = "btn-search"><a href= "https://search.naver.com/search.naver?where=nexearchie=utf8&query=${
                      data.title
                    }" target="_blank">더 알아보기 &nbsp; <i class="far fa-search-location"></i></a></button>
			    				</div>`

      li.innerHTML = content
      mlist.appendChild(li)
      $('.dataImg').style.width = '40%'
    } catch (e) {
      console.log(e)
    }
  }
}

// 인포윈도우를 표시하는 클로저를 만드는 함수
function makeOverListener(map, marker, infowindow) {
  return function () {
    infowindow.open(map, marker)
  }
}

// 인포윈도우를 닫는 클로저를 만드는 함수
function makeOutListener(infowindow) {
  return function () {
    infowindow.close()
  }
}
