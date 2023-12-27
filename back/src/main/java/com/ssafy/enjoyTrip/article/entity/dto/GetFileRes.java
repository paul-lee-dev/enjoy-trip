package com.ssafy.enjoyTrip.article.entity.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GetFileRes {
    private int fileID;
    private int articleId;
    private String saveFolder;
    private String originalFile;
    private String saveFile;
}
