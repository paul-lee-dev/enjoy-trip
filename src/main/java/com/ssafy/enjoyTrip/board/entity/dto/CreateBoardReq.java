package com.ssafy.enjoyTrip.board.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@AllArgsConstructor
public class CreateBoardReq {
    @NotBlank(message="제목을 입력해주세요.")
    private String subject;

    @NotBlank(message="내용을 입력해주세요.")
    private String content;
}
