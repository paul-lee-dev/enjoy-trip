package com.ssafy.enjoyTrip.comment.service;

import com.ssafy.enjoyTrip.comment.dao.CommentDao;
import com.ssafy.enjoyTrip.article.entity.dto.CreateCommentReq;
import com.ssafy.enjoyTrip.article.entity.dto.GetCommentRes;
import com.ssafy.enjoyTrip.article.entity.dto.ModifyCommentReq;
import com.ssafy.enjoyTrip.common.BaseException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.ssafy.enjoyTrip.common.BaseResponseStatus.DB_ERROR;

@Service
public class CommentServiceImpl implements CommentService {

    private final CommentDao commentDao;
    public CommentServiceImpl(CommentDao commentDao){ this.commentDao = commentDao; }

    /**
     * 댓글 등록
     * @param createCommentReq
     * @throws BaseException
     */
    @Transactional
    public void createComment(CreateCommentReq createCommentReq) throws BaseException {
//        List<FileInfo> fileInfos = createCommentReq.getFileInfos();
        try {
            commentDao.createComment(createCommentReq);
        } catch (Exception e){
            e.printStackTrace();
            throw new BaseException(DB_ERROR);
        }
    }

    @Override
    public List<GetCommentRes> listComment(int articleId) throws Exception {
        try {
            return commentDao.listComment(articleId);
        } catch (Exception e) {
            e.printStackTrace();
            throw new BaseException(DB_ERROR);
        }
    }

//    @Override
//    public PageNavigation makePageNavigation(Map<String, String> map) throws Exception {
//        PageNavigation pageNavigation = new PageNavigation();
//
//        int naviSize = SizeConstant.NAVIGATION_SIZE;
//        int sizePerPage = SizeConstant.LIST_SIZE;
//        int currentPage = Integer.parseInt(map.get("pgno"));
//
//        pageNavigation.setCurrentPage(currentPage);
//        pageNavigation.setNaviSize(naviSize);
//        Map<String, Object> param = new HashMap<String, Object>();
//        String key = map.get("key");
//        if ("userid".equals(key))
//            key = "user_id";
//        param.put("key", key == null ? "" : key);
//        param.put("word", map.get("word") == null ? "" : map.get("word"));
//        int totalCount = commentDao.getTotalCommentCount(param);
//        pageNavigation.setTotalCount(totalCount);
//        int totalPageCount = (totalCount - 1) / sizePerPage + 1;
//        pageNavigation.setTotalPageCount(totalPageCount);
//        boolean startRange = currentPage <= naviSize;
//        pageNavigation.setStartRange(startRange);
//        boolean endRange = (totalPageCount - 1) / naviSize * naviSize < currentPage;
//        pageNavigation.setEndRange(endRange);
//        pageNavigation.makeNavigator();
//
//        return pageNavigation;
//    }

    @Override
    public void modifyComment(ModifyCommentReq modifyCommentReq) throws BaseException {
        commentDao.modifyComment(modifyCommentReq);
    }

    @Override
    public void deleteComment(int commentId) throws BaseException {
        commentDao.deleteComment(commentId);
    }
}
