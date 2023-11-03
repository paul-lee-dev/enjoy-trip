package com.ssafy.enjoyTrip.board.service;

import com.ssafy.enjoyTrip.board.entity.dto.CreateBoardReq;
import com.ssafy.enjoyTrip.board.entity.dto.GetBoardRes;
import com.ssafy.enjoyTrip.board.entity.dto.ModifyBoardReq;
import com.ssafy.enjoyTrip.common.BaseException;

public interface BoardService {
    void create(CreateBoardReq createBoardReq) throws BaseException;
    void modifyBoard(ModifyBoardReq modifyBoardReq) throws BaseException;
    void deleteBoard(int article_id) throws BaseException;
//    GetBoardRes listBoard
}
