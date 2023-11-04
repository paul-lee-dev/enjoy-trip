package com.ssafy.enjoyTrip.board.service;

import com.ssafy.enjoyTrip.board.entity.dto.CreateBoardReq;
import com.ssafy.enjoyTrip.board.entity.dto.GetBoardRes;
import com.ssafy.enjoyTrip.board.entity.dto.ModifyBoardReq;
import com.ssafy.enjoyTrip.common.BaseException;
import com.ssafy.enjoyTrip.common.PageNavigation;

import java.util.List;
import java.util.Map;

public interface BoardService {
    void createBoard(CreateBoardReq createBoardReq) throws BaseException;
    List<GetBoardRes> listBoard(Map<String, String> map) throws Exception;
    PageNavigation makePageNavigation(Map<String, String> map) throws Exception;
    GetBoardRes getBoard(int articleId) throws Exception;
    void updateHit(int articleId) throws Exception;
    void modifyBoard(ModifyBoardReq modifyBoardReq) throws BaseException;
    void deleteBoard(int articleId) throws BaseException;
}
