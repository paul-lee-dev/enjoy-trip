package com.ssafy.enjoyTrip.article;

import com.ssafy.enjoyTrip.article.entity.dto.*;
import com.ssafy.enjoyTrip.article.service.ArticleService;
import com.ssafy.enjoyTrip.article.service.ArticleServiceImpl;
import com.ssafy.enjoyTrip.common.BaseException;
import com.ssafy.enjoyTrip.common.BaseResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.apache.commons.io.FileUtils;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.File;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.*;

@Api(value = "Article API")
@CrossOrigin(origins = {"*"})//다른 서버에서 AJax 요청이 와도 서비스 되도록 설정
@RestController
@RequestMapping("/articles")
public class ArticleController {
    private final ArticleService articleService;
    public ArticleController(ArticleServiceImpl service) { this.articleService = service; }

    private static final String SUCCESS = "success";
    private static final String FAIL = "fail";

    private final Logger logger = LoggerFactory.getLogger(ArticleController.class);

    @Autowired
    private ServletContext servletContext;
    /** 게시판 **/
    @ApiOperation(value = "글쓰기")
    @PostMapping
    @Transactional
    public ResponseEntity<?> createArticle(@RequestBody @Valid CreateArticleReq createArticleReq) throws BaseException {
        articleService.createArticle(createArticleReq);
        return ResponseEntity
                .ok()
                .build();
    }

    @ApiOperation(value = "글 목록 조회")
    @GetMapping
    public ResponseEntity<?> listArticle(@ApiParam(value="페이지 번호(default 1)") @RequestParam(defaultValue = "1") Integer pgno,
                                       @ApiParam(value="검색조건 / subject, article_id, user_id", example = "subject") @RequestParam(required=false) String key,
                                       @ApiParam(value="검색어") @RequestParam(required=false) String word) throws Exception {
        Map<String, String> map = new HashMap<>();
        map.put("pgno", ""+pgno);
        if (key != null) map.put("key", key);
        if (key.equals("userId")) map.put("key", "user_id");
        if (word != null) map.put("word", word);
        articleService.listArticle(map);

        return ResponseEntity
                .ok()
                .body(articleService.listArticle(map));
    }

    @ApiOperation(value = "글 상세 조회")
    @GetMapping("/{articleId}")
    public ResponseEntity<?> getArticle(@PathVariable int articleId) throws Exception {
        GetArticleRes getArticleRes = articleService.getArticle(articleId);
        List<GetCommentRes> comments = articleService.listComment(articleId);
        articleService.updateHit(articleId);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("article", getArticleRes);
        map.put("comments", comments);
        return ResponseEntity
                .ok()
                .body(new BaseResponse<>(map));
    }

    @ApiOperation(value = "글 수정")
    @PatchMapping
    public ResponseEntity<?> modifyArticle(@RequestBody ModifyArticleReq modifyArticleReq) throws BaseException {
        articleService.modifyArticle(modifyArticleReq);
        return ResponseEntity
                .ok()
                .build();
    }

    @ApiOperation(value = "글 삭제")
    @DeleteMapping("/{articleId}")
    @Transactional
    public ResponseEntity<?> deleteArticle(@PathVariable int articleId) throws BaseException {
        articleService.deleteArticle(articleId);
        return ResponseEntity
                .ok()
                .build();
    }

    /** 댓글 **/
    @ApiOperation(value = "댓글 작성")
    @PostMapping("/comments")
    @Transactional
    public ResponseEntity<?> createComment(@RequestBody @Valid CreateCommentReq createCommentReq) throws BaseException {
        articleService.createComment(createCommentReq);
        return ResponseEntity
                .ok()
                .build();
    }

    @ApiOperation(value = "댓글 수정")
    @PatchMapping("/comments/{commentId}")
    public ResponseEntity<?> modifyComment(@RequestBody ModifyCommentReq modifyCommentReq) throws BaseException {
        articleService.modifyComment(modifyCommentReq);
        return ResponseEntity
                .ok()
                .build();
    }

    @ApiOperation(value = "댓글 삭제")
    @DeleteMapping("/comments/{commentId}")
    @Transactional
    public ResponseEntity<?> deleteComment(@PathVariable int commentId) throws BaseException {
        articleService.deleteComment(commentId);
        return ResponseEntity
                .ok()
                .build();
    }

    /** 좋아요 **/

    @ApiOperation(value = "좋아요 업데이트")
    @PostMapping("/hearts")
    @Transactional
    public ResponseEntity<?> updateHeart(@RequestBody HeartDto heartDto) throws BaseException {
        if(articleService.exitHeart(heartDto) > 0){
            articleService.exitHeart(heartDto);
        }else {
            articleService.addHeart(heartDto);
        }
        return ResponseEntity
                .ok()
                .build();
    }

    @ApiOperation(value = "좋아요 개수")
    @PostMapping("/hearts/get")
    public ResponseEntity<?> getHeart(@RequestBody HeartDto heartDto) throws BaseException {
        articleService.heartState(heartDto);
        articleService.cntHeart(heartDto.getArticleId());
        return ResponseEntity
                .ok()
                .build();
    }

    /** 파일 **/
    @ApiOperation(value="파일 업로드")
    @PostMapping("/files")
    public ResponseEntity<Map<String, Object>> uploadFile(@RequestParam("upfile") MultipartFile[] files, int articleId) {
        Map<String, Object> map = new HashMap<String, Object>();
        HttpStatus status = null;
        try {
            logger.debug("MultipartFile.isEmpty : {}", files[0].isEmpty());
            if (!files[0].isEmpty()) {
                String realPath = servletContext.getRealPath("/upload");
                String today = new SimpleDateFormat("yyMMdd").format(new Date());
                String saveFolder = realPath + File.separator + today;
                logger.debug("저장 폴더 : {}", saveFolder);
                File folder = new File(saveFolder);
                if (!folder.exists())
                    folder.mkdirs();
                List<UploadFileReq> fileInfos = new ArrayList<UploadFileReq>();
                for (MultipartFile mfile : files) {
                    UploadFileReq uploadFileReq = new UploadFileReq();
                    uploadFileReq.setArticleId(articleId);
                    String originalFileName = mfile.getOriginalFilename();
                    if (!originalFileName.isEmpty()) {
                        String saveFileName = UUID.randomUUID().toString()
                                + originalFileName.substring(originalFileName.lastIndexOf('.'));
                        uploadFileReq.setSaveFolder(today);
                        uploadFileReq.setOriginalFile(originalFileName);
                        uploadFileReq.setSaveFile(saveFileName);
                        logger.debug("원본 파일 이름 : {}, 실제 저장 파일 이름 : {}", mfile.getOriginalFilename(), saveFileName);
                        mfile.transferTo(new File(folder, saveFileName));
                    }
                    fileInfos.add(uploadFileReq);
                    articleService.uploadFile(uploadFileReq);
                }
                map.put("imageUrl", fileInfos);
            }

            map.put("message", SUCCESS);
            status = HttpStatus.ACCEPTED;
        } catch (Exception e) {
            map.put("message", FAIL);
            status = HttpStatus.INTERNAL_SERVER_ERROR;
            e.printStackTrace();
        }
        return new ResponseEntity<Map<String, Object>>(map, status);
    }
    @ApiOperation(value = "게시글 내 파일 조회")
    @GetMapping("/files/{articleId}")
    public ResponseEntity<?> getFiles(@PathVariable int articleId) throws Exception {
        List<GetFileRes> files = articleService.listFiles(articleId);
        List<SendFile> fileList = new ArrayList<SendFile>();
        for (int i = 0; i < files.size(); i++) {
            fileList.add(new SendFile(i, files.get(i).getFileID()));
        }
        return ResponseEntity
                .ok()
                .body(new BaseResponse<>(fileList));
    }

    @ApiOperation(value = "파일 다운로드")
    @GetMapping("/files/download/{fileId}")
    public void getfile(HttpServletResponse response, @PathVariable("fileId") int fileId) {
        Map<String, Object> map = new HashMap<String, Object>();
        HttpStatus status = null;

        try {
            GetFileRes files = articleService.getFile(fileId);
            String realPath = servletContext.getRealPath("/upload");

            // db에서 폴더명 조회
            String folderName = files.getSaveFolder();
            String imageName = files.getSaveFile();
            String imagePath = realPath + File.separator + folderName + File.separator + imageName;
                File imagefile = new File(imagePath);
                System.out.println(imagePath);

            byte[] fileByte = FileUtils.readFileToByteArray(imagefile);
            response.setContentType("application/octet-stream");
            response.setHeader("Content-Disposition",
                    "attachment; fileName=\"" + URLEncoder.encode("tistory.png", "UTF-8") + "\";");
            response.setHeader("Content-Transfer-Encoding", "binary");

            response.getOutputStream().write(fileByte);
            response.getOutputStream().flush();
            response.getOutputStream().close();

        } catch (Exception e) {
//            e.printStackTrace();
        }
    }

    @ApiOperation(value = "파일 삭제")
    @DeleteMapping("/files/{fileId}")
    @Transactional
    public ResponseEntity<?> deleteFile(@PathVariable int fileId) throws BaseException {
        articleService.deleteFile(fileId);
        return ResponseEntity
                .ok()
                .build();
    }


    @ApiOperation(value = "파일 삭제")
    @DeleteMapping("/files/all/{articleId}")
    @Transactional
    public ResponseEntity<?> deleteFiles(@PathVariable int articleId) throws BaseException {
        articleService.deleteAll(articleId);
        return ResponseEntity
                .ok()
                .build();
    }

    static class SendFile {
        int i, idx;
        public SendFile(int i, int idx) {
            this.i = i;
            this.idx = idx;
        }
        public int getI() {
            return i;
        }
        public void setI(int i) {
            this.i = i;
        }
        public int getIdx() {
            return idx;
        }
        public void setIdx(int idx) {
            this.idx = idx;
        }
    }
}
