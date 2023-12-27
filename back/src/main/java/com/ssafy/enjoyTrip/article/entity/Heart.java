package com.ssafy.enjoyTrip.article.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder
public class Heart {
    private int articleId;
    private int userId;
    private int state;
}
