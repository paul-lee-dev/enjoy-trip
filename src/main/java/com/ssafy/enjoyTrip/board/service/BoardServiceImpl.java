package com.ssafy.enjoyTrip.board.service;

import com.ssafy.enjoyTrip.board.dao.BoardDao;
import com.ssafy.enjoyTrip.board.entity.dto.FileInfo;
import com.ssafy.enjoyTrip.board.entity.dto.CreateBoardReq;
import com.ssafy.enjoyTrip.board.entity.dto.GetBoardRes;
import com.ssafy.enjoyTrip.board.entity.dto.ModifyBoardReq;
import com.ssafy.enjoyTrip.common.BaseException;
import com.ssafy.enjoyTrip.common.PageNavigation;
import com.ssafy.enjoyTrip.common.constant.SizeConstant;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.ssafy.enjoyTrip.common.BaseResponseStatus.DB_ERROR;

@Service
public class BoardServiceImpl implements BoardService{

    private final BoardDao boardDao;
    public BoardServiceImpl(BoardDao boardDao){ this.boardDao = boardDao; }

    /**
     * 게시판 글 등록
     * @param createBoardReq
     * @throws BaseException
     */
    @Transactional
    public void createBoard(CreateBoardReq createBoardReq) throws BaseException {
//        List<FileInfo> fileInfos = createBoardReq.getFileInfos();
        try {
            boardDao.createBoard(createBoardReq);
        } catch (Exception e){
            e.printStackTrace();
            throw new BaseException(DB_ERROR);
        }
    }

    @Override
    public List<GetBoardRes> listBoard(Map<String, String> map) throws Exception {
        Map<String, Object> param = new HashMap<String, Object>();
        String key = map.get("key");
        if("userId".equals(key))
            key = "userId";
        param.put("key", key == null ? "" : key);
        param.put("word", map.get("word") == null ? "" : map.get("word"));
        int pgNo = Integer.parseInt(map.get("pgno") == null ? "1" : map.get("pgno"));
        int start = pgNo * SizeConstant.LIST_SIZE - SizeConstant.LIST_SIZE;
        param.put("start", start);
        param.put("listsize", SizeConstant.LIST_SIZE);

        return boardDao.listBoard(param);
    }

    @Override
    public PageNavigation makePageNavigation(Map<String, String> map) throws Exception {
        PageNavigation pageNavigation = new PageNavigation();

        int naviSize = SizeConstant.NAVIGATION_SIZE;
        int sizePerPage = SizeConstant.LIST_SIZE;
        int currentPage = Integer.parseInt(map.get("pgno"));

        pageNavigation.setCurrentPage(currentPage);
        pageNavigation.setNaviSize(naviSize);
        Map<String, Object> param = new HashMap<String, Object>();
        String key = map.get("key");
        if ("userid".equals(key))
            key = "user_id";
        param.put("key", key == null ? "" : key);
        param.put("word", map.get("word") == null ? "" : map.get("word"));
        int totalCount = boardDao.getTotalBoardCount(param);
        pageNavigation.setTotalCount(totalCount);
        int totalPageCount = (totalCount - 1) / sizePerPage + 1;
        pageNavigation.setTotalPageCount(totalPageCount);
        boolean startRange = currentPage <= naviSize;
        pageNavigation.setStartRange(startRange);
        boolean endRange = (totalPageCount - 1) / naviSize * naviSize < currentPage;
        pageNavigation.setEndRange(endRange);
        pageNavigation.makeNavigator();

        return pageNavigation;
    }

    @Override
    public GetBoardRes getBoard(int articleId) throws Exception {
        return boardDao.getBoard(articleId);
    }

    @Override
    public void updateHit(int articleId) throws Exception {
        boardDao.updateHit(articleId);
    }

    @Override
    public void modifyBoard(ModifyBoardReq modifyBoardReq) throws BaseException {
        boardDao.modifyBoard(modifyBoardReq);
    }

    @Override
    public void deleteBoard(int articleId) throws BaseException {
//        List<FileInfo> fileList = boardDao.fileInfoList(articleId);
        boardDao.deleteBoard(articleId);
//        boardDao.deleteFile(articleId);
//        for(FileInfo fileInfo : fileList) {
//            File file = new File(path + File.separator + fileInfo.getSaveFolder() + File.separator + fileInfo.getSaveFile());
//            file.delete();
//        }
    }
}
