import { localAxios } from '@/util/http-commons'

const local = localAxios()

const domain = '/plans'

function listPlan(params, success, fail) {
  let uri = `${domain}?`

  if (params.keyword != null) uri = uri + `keyword=${params.keyword}&`
  if (params.pageNo != null) uri = uri + `pageNo=${params.pageNo}&`
  if (params.userId != null) uri = uri + `userId=${params.userId}&`
  uri = uri.slice(0, -1)

  local.get(uri).then(success).catch(fail)
}

/**
 * plan 페이징 - 총 페이지 수 반환
 * @param {*} success
 * @param {*} fail
 */
function getPageCnt(success, fail) {
  local.get(`${domain}/pagecnt`).then(success).catch(fail)
}

function detailPlan(planId, success, fail) {
  local.get(`${domain}/${planId}`).then(success).catch(fail)
}

function registPlan(plan, success, fail) {
  local.post(`${domain}`, JSON.stringify(plan)).then(success).catch(fail)
}

/**
 * 다른 계획 복사
 * @param {*} params : userId, targetPlanId, scope('PUBLIC' or 'PRIVATE')
 * @param {*} success
 * @param {*} fail
 */
function copyPlan(params, success, fail) {
  local.post(`${domain}`, JSON.stringify(params)).then(success).catch(fail)
}

/**
 * 계획에 관광지 추가
 * @param {*} params : planId, attractionId
 * @param {*} success
 * @param {*} fail
 */
function registPlanList(params, success, fail) {
  local.post(`${domain}/planlists`, JSON.stringify(params)).then(success).catch(fail)
}

/**
 * 계획 - 관광지 방문 순서 수정
 * @param {*} params : planId, orderString
 * / orderString 예시 : " " + planlistId -> ex) " 1 2 3 4"
 * @param {*} success
 * @param {*} fail
 */
function modifyOrder(params, success, fail) {
  local.patch(`${domain}`, JSON.stringify(params)).then(success).catch(fail)
}

function deletePlan(planId, success, fail) {
  local.delete(`${domain}/${planId}`).then(success).catch(fail)
}

function deletePlanList(planListId, success, fail) {
  local.delete(`${domain}/planlists/${planListId}`).then(success).catch(fail)
}

/**
 *
 * @param {*} params : planId, userId
 * @param {*} success
 * @param {*} fail
 */
function like(params, success, fail) {
  local.post(`${domain}/like`, JSON.stringify(params)).then(success).catch(fail)
}

/**
 *
 * @param {*} params : planId, userId
 * @param {*} success
 * @param {*} fail
 */
function dislike(params, success, fail) {
  local.delete(`${domain}/like`, JSON.stringify(params)).then(success).catch(fail)
}

export {
  listPlan,
  getPageCnt,
  detailPlan,
  registPlan,
  copyPlan,
  registPlanList,
  modifyOrder,
  deletePlan,
  deletePlanList,
  like,
  dislike
}
