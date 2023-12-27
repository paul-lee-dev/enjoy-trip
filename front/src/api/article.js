import { localAxios } from '@/util/http-commons'

const local = localAxios()

const domain = '/articles'
const comment_domain = domain + '/comments'

function listArticle(param, success, fail) {
  local.get(`${domain}`, { params: param }).then(success).catch(fail)
}

function detailArticle(articleId, success, fail) {
  local.get(`${domain}/${articleId}`).then(success).catch(fail)
}

function registArticle(article, success, fail) {
  console.log('articlejs article', article)
  local.post(`${domain}`, JSON.stringify(article)).then(success).catch(fail)
}

// // router로 대체
// function getModifyArticle(articleId, success, fail) {
//   local.get(`${domain}/modify/${articleId}`).then(success).catch(fail)
// }

function modifyArticle(article, success, fail) {
  local.patch(`${domain}`, JSON.stringify(article)).then(success).catch(fail)
}

function deleteArticle(articleId, success, fail) {
  local.delete(`${domain}/${articleId}`).then(success).catch(fail)
}

/**
 *
 * @param {*} comment : articleId, parentCommentId, content, userId
 * @param {*} success
 * @param {*} fail
 */
function registComment(comment, success, fail) {
  local.post(`${comment_domain}`, JSON.stringify(comment)).then(success).catch(fail)
}

/**
 *
 * @param {} comment : commentId, articleId, parentCommentId, userId, content
 * @param {*} success
 * @param {*} fail
 */
function modifyComment(comment, success, fail) {
  local
    .patch(`${comment_domain}/${comment.commentId}`, JSON.stringify(comment))
    .then(success)
    .catch(fail)
}

function deleteComment(commentId, success, fail) {
  local.delete(`${comment_domain}/${commentId}`).then(success).catch(fail)
}

export {
  listArticle,
  detailArticle,
  registArticle,
  modifyArticle,
  deleteArticle,
  registComment,
  modifyComment,
  deleteComment
}
