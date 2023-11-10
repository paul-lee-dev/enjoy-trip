package com.ssafy.enjoyTrip.board;

import com.ssafy.enjoyTrip.board.entity.dto.CreateBoardReq;
<<<<<<< HEAD
import com.ssafy.enjoyTrip.board.entity.dto.GetBoardRes;
=======
>>>>>>> 8c65e11 (Board controller, mapper 완료)
import com.ssafy.enjoyTrip.board.entity.dto.ModifyBoardReq;
import com.ssafy.enjoyTrip.board.service.BoardService;
import com.ssafy.enjoyTrip.board.service.BoardServiceImpl;
import com.ssafy.enjoyTrip.common.BaseException;
<<<<<<< HEAD
import com.ssafy.enjoyTrip.common.BaseResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
=======
>>>>>>> 8c65e11 (Board controller, mapper 완료)
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
<<<<<<< HEAD
import java.util.HashMap;
import java.util.Map;

@Api(value = "Board API")
=======
import java.util.Map;

>>>>>>> 8c65e11 (Board controller, mapper 완료)
@RestController
@RequestMapping("/board")
public class BoardController {
    private final BoardService boardService;

    public  BoardController(BoardServiceImpl service) { this.boardService = service; }

<<<<<<< HEAD
    @ApiOperation(value = "글쓰기")
=======
>>>>>>> 8c65e11 (Board controller, mapper 완료)
    @PostMapping("/write")
    @Transactional
    public ResponseEntity<?> createBoard(@RequestBody @Valid CreateBoardReq createBoardReq) throws BaseException {
        boardService.createBoard(createBoardReq);
        return ResponseEntity
                .ok()
                .build();
    }

<<<<<<< HEAD
    @ApiOperation(value = "글 목록 조회")
    @GetMapping("/list")
    public ResponseEntity<?> listBoard(@ApiParam(value="페이지 번호(default 1)") @RequestParam(defaultValue = "1") Integer pgno,
                                       @ApiParam(value="검색조건 / subject, article_id, user_id", example = "subject") @RequestParam(required=false) String key,
                                       @ApiParam(value="검색어") @RequestParam(required=false) String word) throws Exception {
        Map<String, String> map = new HashMap<>();
        map.put("pgno", ""+pgno);
        if (key != null) map.put("key", key);
        if (word != null) map.put("word", word);
=======
    @GetMapping("/list")
    public ResponseEntity<?> listBoard(@RequestBody @Valid Map<String, String> map) throws Exception {
>>>>>>> 8c65e11 (Board controller, mapper 완료)
        boardService.listBoard(map);

        return ResponseEntity
                .ok()
<<<<<<< HEAD
                .body(boardService.listBoard(map));
    }

    @ApiOperation(value = "글 상세 조회")
    @GetMapping("/view/{articleId}")
    public ResponseEntity<?> getBoard(@PathVariable int articleId) throws Exception {
        GetBoardRes getBoardRes = boardService.getBoard(articleId);
        boardService.updateHit(articleId);
        return ResponseEntity
                .ok()
                .body(new BaseResponse<>(getBoardRes));
    }

    @ApiOperation(value = "글 수정")
=======
                .build();
    }

    @GetMapping("/view/{articleId}")
    public ResponseEntity<?> getBoard(@PathVariable int articleId) throws Exception {
        boardService.getBoard(articleId);
        boardService.updateHit(articleId);
        return ResponseEntity
                .ok()
                .build();
    }

>>>>>>> 8c65e11 (Board controller, mapper 완료)
    @PatchMapping("/modify")
    public ResponseEntity<?> modifyBoard(@RequestBody @Valid ModifyBoardReq modifyBoardReq) throws BaseException {
        boardService.modifyBoard(modifyBoardReq);
        return ResponseEntity
                .ok()
                .build();
    }
<<<<<<< HEAD

    @ApiOperation(value = "글 삭제")
=======
>>>>>>> 8c65e11 (Board controller, mapper 완료)
    @PostMapping("/delete/{articleId}")
    @Transactional
    public ResponseEntity<?> deleteBoard(@PathVariable int articleId) throws BaseException {
        boardService.deleteBoard(articleId);
        return ResponseEntity
                .ok()
                .build();
    }
}
