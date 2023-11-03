package com.ssafy.enjoyTrip.board.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import java.util.List;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class GetBoardRes {
    private int articleId;
    private int userId;
    private String subject;
    private String content;
    private int hit;
    private String createdAt;
    private String updatedAt;
//    private List<FileInfo> fileInfos;
}
