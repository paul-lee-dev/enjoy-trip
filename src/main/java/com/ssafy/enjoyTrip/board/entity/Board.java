package com.ssafy.enjoyTrip.board.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder
public class Board {
    private int article_id;
    private int user_id;
    private String subject;
    private String content;
    private int hit;
    private String created_at;
    private String updated_at;
}
