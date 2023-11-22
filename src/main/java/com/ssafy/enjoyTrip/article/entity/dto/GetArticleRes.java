package com.ssafy.enjoyTrip.article.entity.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class GetArticleRes {
    private int articleId;
    private int userId;
    private String userNickname;
    private String subject;
    private String content;
    private int hit;
    private String createdAt;
    private String updatedAt;
    private int heart;
}
