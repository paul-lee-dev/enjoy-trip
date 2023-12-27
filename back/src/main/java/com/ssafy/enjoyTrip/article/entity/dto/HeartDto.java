package com.ssafy.enjoyTrip.article.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class HeartDto {
    private int articleId;
    private int userId;
    private int state;
}