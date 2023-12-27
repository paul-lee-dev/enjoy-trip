package com.ssafy.enjoyTrip.article.entity.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
public class ModifyArticleReq {
    @NotNull(message="article_id is null")
    private int articleId;

    @NotNull(message="user_id is null")
    private int userId;

    @NotBlank(message="제목을 입력해주세요.")
    private String subject;

    @NotBlank(message="내용을 입력해주세요.")
    private String content;
}
