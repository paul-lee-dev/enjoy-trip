import { localAxios } from '@/util/http-commons'

const local = localAxios()

const domain = '/users'

function login(params, success, fail) {
  local.post(`${domain}/login`, JSON.stringify(params)).then(success).catch(fail)
}

function registUser(user, success, fail) {
  local.post(`${domain}`, JSON.stringify(user)).then(success).catch(fail)
}

function listUser(success, fail) {
  local.get(`${domain}`).then(success).catch(fail)
}

function logout(success, fail) {
  local.get(`${domain}/logout`).then(success).catch(fail)
}

function detailUser(userId, success, fail) {
  local.get(`${domain}/${userId}`).then(success).catch(fail)
}

/**
 * @param {*} user : userId, email, nickname, profileImgUrl, phoneNumber
 * @param {*} success
 * @param {*} fail
 */
function modifyUser(user, success, fail) {
  console.log('in api, user = ', user)
  local.post(`${domain}`).then(success).catch(fail)
}

function deleteUser(userId, success, fail) {
  local.post(`${domain}/delete/${userId}`).then(success).catch(fail)
}

export { login, registUser, logout, listUser, detailUser, modifyUser, deleteUser }
