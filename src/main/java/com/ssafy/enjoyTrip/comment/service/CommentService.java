package com.ssafy.enjoyTrip.comment.service;

import com.ssafy.enjoyTrip.comment.entity.dto.CreateCommentReq;
import com.ssafy.enjoyTrip.comment.entity.dto.GetCommentRes;
import com.ssafy.enjoyTrip.comment.entity.dto.ModifyCommentReq;
import com.ssafy.enjoyTrip.common.BaseException;
import com.ssafy.enjoyTrip.common.PageNavigation;

import java.util.List;
import java.util.Map;

public interface CommentService {
    void createComment(CreateCommentReq createCommentReq) throws BaseException;
    List<GetCommentRes> listComment(int articleNo) throws Exception;
//    PageNavigation makePageNavigation(Map<String, String> map) throws Exception;
    void modifyComment(ModifyCommentReq modifyCommentReq) throws BaseException;
    void deleteComment(int commentId) throws BaseException;
}
