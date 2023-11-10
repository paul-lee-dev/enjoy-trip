package com.ssafy.enjoyTrip.comment.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder
public class Comment {
    private int commentId;
    private int userId;
    private String content;
    private int articleId;
    private int parentCommentId;
    private String updatedAt;
}
