package com.ssafy.enjoyTrip.article.entity.dto;

import lombok.*;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GetCommentRes {
    private int commentId;
    private int userId;
    private String userNickname;
    private Integer parentCommentId;

    @NotBlank(message="내용을 입력해주세요.")
    private String content;

    private String updatedAt;
}
