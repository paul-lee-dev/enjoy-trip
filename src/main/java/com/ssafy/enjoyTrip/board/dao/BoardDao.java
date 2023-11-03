package com.ssafy.enjoyTrip.board.dao;

import com.ssafy.enjoyTrip.board.entity.dto.CreateBoardReq;
import com.ssafy.enjoyTrip.board.entity.dto.GetBoardRes;
import com.ssafy.enjoyTrip.board.entity.dto.ModifyBoardReq;

import java.util.List;

public interface BoardDao {
    void createBoard(CreateBoardReq createBoardReq);
    GetBoardRes findByUserId(int userId);
    GetBoardRes findByArticleId(int articleId);
    GetBoardRes findBySubject(String subject);
    void modifyBoard(ModifyBoardReq modifyBoardReq);
    List<GetBoardRes> findAll();
}
