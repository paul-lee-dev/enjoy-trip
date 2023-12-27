import { localAxios } from '@/util/http-commons'

const local = localAxios()

const domain = '/spots'

function getSido(success, fail) {
  local.get(`${domain}`).then(success).catch(fail)
}

function getGugun(sidoCode, success, fail) {
  local.get(`${domain}/${sidoCode}`).then(success).catch(fail)
}

/**
 * 지역 & 타입별 관광지 검색
 * @param {} params : contentId, sidoCode, gugunCode
 * @param {*} success
 * @param {*} fail
 */
function getSpotList(params, success, fail) {
  local
    .get(`${domain}/${params.contentId}/${params.sidoCode}/${params.gugunCode}`)
    .then(success)
    .catch(fail)
}

/**
 * 키워드로 관광지 검색
 * @param {*} word : 검색 키워드
 * @param {*} success
 * @param {*} fail
 */
function searchSpot(word, success, fail) {
  local.get(`${domain}/search/${word}`).then(success).catch(fail)
}

function getSpotDetail(spotId, success, fail) {
  local.get(`${domain}/detail/${spotId}`).then(success).catch(fail)
}

export { getSido, getGugun, getSpotList, searchSpot, getSpotDetail }
