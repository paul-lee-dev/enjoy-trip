package com.ssafy.enjoyTrip.board.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class GetArticleRes {
    private int article_id;
    private int user_id;
    private String subject;
    private String content;
    private int hit;
    private String created_at;
    private String updated_at;
}
