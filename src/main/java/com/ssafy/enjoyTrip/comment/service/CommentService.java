package com.ssafy.enjoyTrip.comment.service;

import com.ssafy.enjoyTrip.article.entity.dto.CreateCommentReq;
import com.ssafy.enjoyTrip.article.entity.dto.GetCommentRes;
import com.ssafy.enjoyTrip.article.entity.dto.ModifyCommentReq;
import com.ssafy.enjoyTrip.common.BaseException;

import java.util.List;

public interface CommentService {
    void createComment(CreateCommentReq createCommentReq) throws BaseException;
    List<GetCommentRes> listComment(int articleNo) throws Exception;
//    PageNavigation makePageNavigation(Map<String, String> map) throws Exception;
    void modifyComment(ModifyCommentReq modifyCommentReq) throws BaseException;
    void deleteComment(int commentId) throws BaseException;
}
