package com.ssafy.enjoyTrip.article.entity;

import lombok.*;

@Getter
@AllArgsConstructor
@Builder
public class FileInfo {
    private int fileID;
    private int articleId;
    private String saveFolder;
    private String originalFile;
    private String saveFile;
}
