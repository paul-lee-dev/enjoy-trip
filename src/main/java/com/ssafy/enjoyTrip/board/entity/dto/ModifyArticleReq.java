package com.ssafy.enjoyTrip.board.entity.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@NoArgsConstructor
public class ModifyArticleReq {
    @NotBlank(message="article is null")
    private int article_id;

    @NotBlank(message="user_id is null")
    private int user_id;

    @NotBlank(message="제목을 입력해주세요.")
    private String subject;

    @NotBlank(message="내용을 입력해주세요.")
    private String content;
}
