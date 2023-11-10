package com.ssafy.enjoyTrip.comment.entity.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GetCommentRes {
    private int commentId;
    private int userId;
    private String parentCommentId;
    private String content;
    private String updatedAt;
}
