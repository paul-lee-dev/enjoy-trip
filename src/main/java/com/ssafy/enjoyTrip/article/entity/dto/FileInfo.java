package com.ssafy.enjoyTrip.article.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class FileInfo {
    private String saveFolder;
    private String originalFile;
    private String saveFile;
}
