package com.ssafy.enjoyTrip.article.service;

import com.ssafy.enjoyTrip.article.entity.dto.CreateArticleReq;
import com.ssafy.enjoyTrip.article.entity.dto.GetArticleRes;
import com.ssafy.enjoyTrip.article.entity.dto.ModifyArticleReq;
import com.ssafy.enjoyTrip.common.BaseException;
import com.ssafy.enjoyTrip.common.PageNavigation;

import java.util.List;
import java.util.Map;

public interface ArticleService {
    void createArticle(CreateArticleReq createArticleReq) throws BaseException;
    List<GetArticleRes> listArticle(Map<String, String> map) throws Exception;

    PageNavigation makePageNavigation(Map<String, String> map) throws Exception;
    GetArticleRes getArticle(int articleId) throws Exception;
    void updateHit(int articleId) throws Exception;
    void modifyArticle(ModifyArticleReq modifyArticleReq) throws BaseException;
    void deleteArticle(int articleId) throws BaseException;
}
