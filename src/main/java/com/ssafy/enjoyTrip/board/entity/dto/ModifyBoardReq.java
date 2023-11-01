package com.ssafy.enjoyTrip.board.entity.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@NoArgsConstructor
public class ModifyBoardReq {
    @NotBlank(message="article_id is null")
    private int articleId;

    @NotBlank(message="user_id is null")
    private int userId;

    @NotBlank(message="제목을 입력해주세요.")
    private String subject;

    @NotBlank(message="내용을 입력해주세요.")
    private String content;
}
