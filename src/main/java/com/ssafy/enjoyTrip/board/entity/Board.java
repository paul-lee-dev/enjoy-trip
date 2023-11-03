package com.ssafy.enjoyTrip.board.entity;

import com.ssafy.enjoyTrip.board.entity.dto.FileInfo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import java.util.List;
@Getter
@AllArgsConstructor
@Builder
public class Board {
    private int articleId;
    private int userId;
    private String subject;
    private String content;
    private int hit;
    private String createdAt;
    private String updatedAt;
//    private List<FileInfo> fileInfos;
}
