package com.ssafy.enjoyTrip.comment.dao;

import com.ssafy.enjoyTrip.comment.entity.dto.CreateCommentReq;
import com.ssafy.enjoyTrip.comment.entity.dto.GetCommentRes;
import com.ssafy.enjoyTrip.comment.entity.dto.ModifyCommentReq;

import java.util.List;

public interface CommentDao {
    void createComment(CreateCommentReq createCommentReq);
    void modifyComment(ModifyCommentReq modifyCommentReq);
    void deleteComment(int commentId);
    List<GetCommentRes> listComment(int articleId);
}
