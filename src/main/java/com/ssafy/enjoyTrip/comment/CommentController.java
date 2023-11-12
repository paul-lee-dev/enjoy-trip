package com.ssafy.enjoyTrip.comment;

import com.ssafy.enjoyTrip.comment.entity.dto.CreateCommentReq;
import com.ssafy.enjoyTrip.comment.entity.dto.GetCommentRes;
import com.ssafy.enjoyTrip.comment.entity.dto.ModifyCommentReq;
import com.ssafy.enjoyTrip.comment.service.CommentService;
import com.ssafy.enjoyTrip.comment.service.CommentServiceImpl;
import com.ssafy.enjoyTrip.common.BaseException;
import com.ssafy.enjoyTrip.common.BaseResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@Api(value = "Comment API")
@CrossOrigin(origins = {"*"})//다른 서버에서 AJax 요청이 와도 서비스 되도록 설정
@RestController
@RequestMapping("/comments")
public class CommentController {
    private final CommentService commentService;

    public CommentController(CommentServiceImpl service) { this.commentService = service; }

    @ApiOperation(value = "댓글 쓰기")
    @PostMapping
    @Transactional
    public ResponseEntity<?> createComment(@RequestBody @Valid CreateCommentReq createCommentReq) throws BaseException {
        commentService.createComment(createCommentReq);
        return ResponseEntity
                .ok()
                .build();
    }

    @ApiOperation(value = "댓글 조회")
    @GetMapping("/{articleId}")
    @Transactional
    public ResponseEntity<?> listComment(@PathVariable @ApiParam(value = "조회할 댓글의 글 id", required = true) int articleId) throws Exception {
        List<GetCommentRes> commentList = commentService.listComment(articleId);
        return ResponseEntity
                .ok()
                .body(new BaseResponse<>(commentList));
    }

    @ApiOperation(value = "댓글 수정")
    @PatchMapping
    public ResponseEntity<?> modifyComment(@RequestBody ModifyCommentReq modifyCommentReq) throws BaseException {
        commentService.modifyComment(modifyCommentReq);
        return ResponseEntity
                .ok()
                .build();
    }


    @ApiOperation(value = "댓글 삭제")
    @DeleteMapping("/{commentId}")
    @Transactional
    public ResponseEntity<?> deleteComment(@PathVariable int commentId) throws BaseException {
        commentService.deleteComment(commentId);
        return ResponseEntity
                .ok()
                .build();
    }
}
