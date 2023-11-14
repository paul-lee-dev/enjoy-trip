package com.ssafy.enjoyTrip.article.entity.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GetArticleRes {
    private int articleId;
    private int userId;
    private String subject;
    private String content;
    private int hit;
    private String createdAt;
    private String updatedAt;
    private int heart;
//    private List<FileInfo> fileInfos;
}
