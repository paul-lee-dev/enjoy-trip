package com.ssafy.enjoyTrip.article.dao;

import com.ssafy.enjoyTrip.article.entity.dto.*;

import java.util.List;
import java.util.Map;

public interface ArticleDao {
    void createArticle(CreateArticleReq createArticleReq);
    //    void registerFile(CreateArticleReq createArticleReq);
    int getTotalArticleCount(Map<String, Object> param);

    void updateHit(int articleId);
    void modifyArticle(ModifyArticleReq modifyArticleReq);
    void deleteArticle(int articleId);
    //    void deleteFile(int articleId);
    List<GetArticleRes> listArticle(Map<String, Object> param);
    //    List<FileInfo> fileInfoList(int articleId);
    GetArticleRes getArticle(int articleId);

    void createComment(CreateCommentReq createCommentReq);
    void modifyComment(ModifyCommentReq modifyCommentReq);
    void deleteComment(int commentId);
    List<GetCommentRes> listComment(int articleId);

}
