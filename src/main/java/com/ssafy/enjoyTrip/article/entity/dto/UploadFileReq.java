package com.ssafy.enjoyTrip.article.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UploadFileReq {
    private int articleId;
    private String saveFolder;
    private String originalFile;
    private String saveFile;
}
