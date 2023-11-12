package com.ssafy.enjoyTrip.comment.entity.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
public class ModifyCommentReq {
    @NotNull(message="article_id is null")
    private int articleId;

    private int parentCommentId;

    @NotNull(message="user_id is null")
    private int userId;

    @NotBlank(message="내용을 입력해주세요.")
    private String content;
}
