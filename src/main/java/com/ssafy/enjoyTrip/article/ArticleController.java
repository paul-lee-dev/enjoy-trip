package com.ssafy.enjoyTrip.article;

import com.ssafy.enjoyTrip.article.entity.dto.CreateArticleReq;
import com.ssafy.enjoyTrip.article.entity.dto.GetArticleRes;
import com.ssafy.enjoyTrip.article.entity.dto.ModifyArticleReq;
import com.ssafy.enjoyTrip.article.service.ArticleService;
import com.ssafy.enjoyTrip.article.service.ArticleServiceImpl;
import com.ssafy.enjoyTrip.common.BaseException;
import com.ssafy.enjoyTrip.common.BaseResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@Api(value = "Article API")
@RestController
@RequestMapping("/articles")
public class ArticleController {
    private final ArticleService articleService;

    public ArticleController(ArticleServiceImpl service) { this.articleService = service; }

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
        articleService.updateHit(articleId);
        return ResponseEntity
                .ok()
                .body(new BaseResponse<>(getArticleRes));
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
}
