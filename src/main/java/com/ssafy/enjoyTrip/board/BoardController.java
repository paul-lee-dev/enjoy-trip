package com.ssafy.enjoyTrip.board;

import com.ssafy.enjoyTrip.board.entity.dto.CreateBoardReq;
import com.ssafy.enjoyTrip.board.entity.dto.ModifyBoardReq;
import com.ssafy.enjoyTrip.board.service.BoardService;
import com.ssafy.enjoyTrip.board.service.BoardServiceImpl;
import com.ssafy.enjoyTrip.common.BaseException;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Map;

@RestController
@RequestMapping("/board")
public class BoardController {
    private final BoardService boardService;

    public  BoardController(BoardServiceImpl service) { this.boardService = service; }

    @PostMapping("/write")
    @Transactional
    public ResponseEntity<?> createBoard(@RequestBody @Valid CreateBoardReq createBoardReq) throws BaseException {
        boardService.createBoard(createBoardReq);
        return ResponseEntity
                .ok()
                .build();
    }

    @GetMapping("/list")
    public ResponseEntity<?> listBoard(@RequestBody @Valid Map<String, String> map) throws Exception {
        boardService.listBoard(map);

        return ResponseEntity
                .ok()
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

    @PatchMapping("/modify")
    public ResponseEntity<?> modifyBoard(@RequestBody @Valid ModifyBoardReq modifyBoardReq) throws BaseException {
        boardService.modifyBoard(modifyBoardReq);
        return ResponseEntity
                .ok()
                .build();
    }
    @PostMapping("/delete/{articleId}")
    @Transactional
    public ResponseEntity<?> deleteBoard(@PathVariable int articleId) throws BaseException {
        boardService.deleteBoard(articleId);
        return ResponseEntity
                .ok()
                .build();
    }
}
