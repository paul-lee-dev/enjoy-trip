package com.ssafy.enjoyTrip.board.dao;

import com.ssafy.enjoyTrip.board.entity.dto.FileInfo;
import com.ssafy.enjoyTrip.board.entity.dto.CreateBoardReq;
import com.ssafy.enjoyTrip.board.entity.dto.GetBoardRes;
import com.ssafy.enjoyTrip.board.entity.dto.ModifyBoardReq;

import java.util.List;
import java.util.Map;

public interface BoardDao {
    void createBoard(CreateBoardReq createBoardReq);
    void registerFile(CreateBoardReq createBoardReq);
    int getTotalBoardCount(Map<String, Object> param);

    void updateHit(int articleId);
    void modifyBoard(ModifyBoardReq modifyBoardReq);
    void deleteBoard(int articleId);
//    void deleteFile(int articleId);
    List<GetBoardRes> listBoard(Map<String, Object> param);
//    List<FileInfo> fileInfoList(int articleId);
    GetBoardRes getBoard(int articleId);
}
