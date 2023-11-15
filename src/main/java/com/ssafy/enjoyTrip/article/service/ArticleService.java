package com.ssafy.enjoyTrip.article.service;

import com.ssafy.enjoyTrip.article.entity.dto.*;
import com.ssafy.enjoyTrip.common.BaseException;
import com.ssafy.enjoyTrip.common.PageNavigation;

import java.util.List;
import java.util.Map;

public interface ArticleService {

    /** 게시판 **/
    void createArticle(CreateArticleReq createArticleReq) throws BaseException;
    List<GetArticleRes> listArticle(Map<String, String> map) throws BaseException;
    PageNavigation makePageNavigation(Map<String, String> map) throws BaseException;
    GetArticleRes getArticle(int articleId) throws Exception;
    void updateHit(int articleId) throws Exception;
    void modifyArticle(ModifyArticleReq modifyArticleReq) throws BaseException;
    void deleteArticle(int articleId) throws BaseException;

    /** 댓글 **/
    void createComment(CreateCommentReq createCommentReq) throws BaseException;
    List<GetCommentRes> listComment(int articleId) throws BaseException;
    void modifyComment(ModifyCommentReq modifyCommentReq) throws BaseException;
    void deleteComment(int commentId) throws BaseException;

    /** 좋아요 **/
    void addHeart(HeartDto heartDto)  throws BaseException;
    void updateheart(HeartDto heartDto)  throws BaseException;
    int heartState(HeartDto heartDto) throws BaseException;
    int exitHeart(HeartDto heartDto) throws BaseException;
    int cntHeart(int articleId) throws BaseException;

    /** 파일업로드 **/
    void uploadFile(UploadFileReq uploadFileReq) throws BaseException;
    List<GetFileRes> listFiles(int articleId) throws BaseException;
    GetFileRes getFile(int fileId) throws BaseException;
    void deleteFile(int fileId) throws BaseException;
    void deleteAll(int articleId) throws BaseException;
    int getAuto() throws BaseException;
}
