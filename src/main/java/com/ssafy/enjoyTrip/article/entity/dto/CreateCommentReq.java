package com.ssafy.enjoyTrip.article.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@AllArgsConstructor
public class CreateCommentReq {
    @NotNull(message="article_id is null")
    private int articleId;
    private int parentCommentId;
    private String content;
    private int userId;
}
