package com.ssafy.enjoyTrip.article.service;

import com.ssafy.enjoyTrip.article.dao.ArticleDao;
import com.ssafy.enjoyTrip.article.entity.dto.*;
import com.ssafy.enjoyTrip.common.BaseException;
import com.ssafy.enjoyTrip.common.PageNavigation;
import com.ssafy.enjoyTrip.common.constant.SizeConstant;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.ssafy.enjoyTrip.common.BaseResponseStatus.DB_ERROR;

@Service
public class ArticleServiceImpl implements ArticleService {

    private final ArticleDao articleDao;
    public ArticleServiceImpl(ArticleDao articleDao){ this.articleDao = articleDao; }

    /**
     * 게시판 글 등록
     * @param createArticleReq
     * @throws BaseException
     */
    @Transactional
    public void createArticle(CreateArticleReq createArticleReq) throws BaseException {
//        List<FileInfo> fileInfos = createArticleReq.getFileInfos();
        try {
            articleDao.createArticle(createArticleReq);
        } catch (Exception e){
            e.printStackTrace();
            throw new BaseException(DB_ERROR);
        }
    }

    @Override
    public List<GetArticleRes> listArticle(Map<String, String> map) throws BaseException {
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

        return articleDao.listArticle(param);
    }

    @Override
    public PageNavigation makePageNavigation(Map<String, String> map) throws BaseException {
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
        int totalCount = articleDao.getTotalArticleCount(param);
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
    public GetArticleRes getArticle(int articleId) throws Exception {
        return articleDao.getArticle(articleId);
    }

    @Override
    public void updateHit(int articleId) throws Exception {
        articleDao.updateHit(articleId);
    }

    @Override
    public void modifyArticle(ModifyArticleReq modifyArticleReq) throws BaseException {
        articleDao.modifyArticle(modifyArticleReq);
    }

    @Override
    public void deleteArticle(int articleId) throws BaseException {
//        List<FileInfo> fileList = articleDao.fileInfoList(articleId);
        articleDao.deleteArticle(articleId);
//        articleDao.deleteFile(articleId);
//        for(FileInfo fileInfo : fileList) {
//            File file = new File(path + File.separator + fileInfo.getSaveFolder() + File.separator + fileInfo.getSaveFile());
//            file.delete();
//        }
    }

    public void createComment(CreateCommentReq createCommentReq) throws BaseException {

    }

    public List<GetCommentRes> listComment(int articleNo) throws BaseException {
        return null;
    }

    public void modifyComment(ModifyCommentReq modifyCommentReq) throws BaseException {

    }

    public void deleteComment(int commentId) throws BaseException {

    }

    public void addHeart(HeartDto heartDto) throws BaseException {

    }

    public void updateheart(HeartDto heartDto) throws BaseException {

    }

    public int heartState(HeartDto heartDto) throws BaseException {
        return 0;
    }

    public int exitHeart(HeartDto heartDto) throws BaseException {
        return 0;
    }

    public int cntHeart(int articleId) throws BaseException {
        return 0;
    }

    public void uploadFile(UploadFileReq uploadFileReq) throws BaseException {

    }

    public List<GetFileRes> listFiles(int articleId) throws BaseException {
        return null;
    }

    public GetFileRes getFile(int fileId) throws BaseException {
        return null;
    }

    public void deleteFile(int fileId) throws BaseException {

    }

    public void deleteAll(int articleId) throws BaseException {

    }
}
