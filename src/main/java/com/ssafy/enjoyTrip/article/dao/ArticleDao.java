package com.ssafy.enjoyTrip.article.dao;

import com.ssafy.enjoyTrip.article.entity.dto.*;

import java.util.List;
import java.util.Map;

public interface ArticleDao {
    /** 게시판 **/
    void createArticle(CreateArticleReq createArticleReq);
    int getTotalArticleCount(Map<String, Object> param);
    List<GetArticleRes> listArticle(Map<String, Object> param);
    void updateHit(int articleId);
    void modifyArticle(ModifyArticleReq modifyArticleReq);
    void deleteArticle(int articleId);
    GetArticleRes getArticle(int articleId);

    /** 댓글 **/
    void createComment(CreateCommentReq createCommentReq);
    void modifyComment(ModifyCommentReq modifyCommentReq);
    void deleteComment(int commentId);
    List<GetCommentRes> listComment(int articleId);

    /** 좋아요 **/
    void addHeart(HeartDto heartDto);
    void updateHeart(HeartDto heartDto);
    int heartState(HeartDto heartDto);
    int exitHeart(HeartDto heartDto);
    int cntHeart(int articleId);

    /** 파일업로드 **/
    void uploadFile(UploadFileReq uploadFileReq);
    List<GetFileRes> listFiles(int articleId);
    GetFileRes getFile(int fileId);
    void deleteFile(int fileId);
    void deleteAll(int articleId);
    int getAuto();
}
