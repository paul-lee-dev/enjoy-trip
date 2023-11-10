package com.ssafy.enjoyTrip.comment.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@AllArgsConstructor
public class CreateCommentReq {
    @NotNull(message="article_id is null")
    private int articleId;

    private int parentCommentId;
    @NotBlank(message="내용을 입력해주세요.")
    private String content;
    @NotBlank(message="내용을 입력해주세요.")
    private int userId;
}
