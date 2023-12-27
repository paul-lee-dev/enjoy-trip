package com.ssafy.enjoyTrip.article.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder
public class Article {
    private int articleId;
    private int userId;
    private String subject;
    private String content;
    private int hit;
    private String createdAt;
    private String updatedAt;
    private int heart;
}
