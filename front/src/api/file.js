import { fileAxios } from '@/util/http-commons'

const local = fileAxios()

const domain = '/files'

/**
 * upload file -> return uri
 * @param {*} file
 * @param {*} success
 * @param {*} fail
 */
function uploadFile(file, success, fail) {
  local.post(`${domain}`, file).then(success).catch(fail)
}

export { uploadFile }
