package com.ssafy.enjoyTrip.article.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class UploadFileReq {
    private int articleId;
    private String saveFolder;
    private String originalFile;
    private String saveFile;
}
