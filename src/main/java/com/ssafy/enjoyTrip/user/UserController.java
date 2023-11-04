package com.ssafy.enjoyTrip.user;

import com.ssafy.enjoyTrip.common.BaseException;
import com.ssafy.enjoyTrip.common.BaseResponse;
import com.ssafy.enjoyTrip.user.entity.dto.*;
import com.ssafy.enjoyTrip.user.service.UserService;
import com.ssafy.enjoyTrip.user.service.UserServiceImpl;
import io.swagger.annotations.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;

/**
 * TODO : 소셜 로그인/로그아웃, 토큰 등 세션유지
 */
@Api(value="User API")
//@CrossOrigin(origins = {"*"})//다른 서버에서 AJax 요청이 와도 서비스 되도록 설정
@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserServiceImpl service) {
        this.userService = service;
    }

    /**
     * 회원가입
     * @param createUserReq
     */
    @ApiOperation(value="회원가입")
    @ApiResponses(value = {
            @ApiResponse(code = 400, message = "입력값 형식 불일치"),
            @ApiResponse(code = 500, message = "서버 에러")
    })
    @PostMapping
    public ResponseEntity<?> join(@RequestBody @Valid @ApiParam CreateUserReq createUserReq) throws BaseException {
        userService.join(createUserReq);

        return ResponseEntity
                .ok()
                .build();
    }

    /**
     * 회원 리스트 조회
     * @return
     * @throws BaseException
     */
    @ApiOperation(value="회원 목록 조회")
    @GetMapping
    public ResponseEntity<?> findAll() throws BaseException {
        List<GetUserRes> userList =userService.findAll();

        return ResponseEntity
                .ok()
                .body(new BaseResponse<>(userList));
    }

    /**
     * 로그인(세션)
     */
    @ApiOperation(value="로그인(세션)")
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginReq loginReq, HttpSession session) throws BaseException {
        userService.login(loginReq, session);
        return ResponseEntity
                .ok()
                .build();
    }

    /**
     * 로그아웃(세션)
     */
    @GetMapping("/logout")
    public ResponseEntity<?> logout(HttpSession session) throws BaseException {
        userService.logout(session);

        return ResponseEntity
                .ok()
                .build();
    }

    /**
     * 회원정보 조회
     * @param userId
     */
    @ApiOperation(value="단일 회원 상세정보 조회")
    @GetMapping("/{userId}")
    public ResponseEntity<?> findById(@PathVariable @ApiParam(value="조회할 유저 id", required = true, example = "1")
                                          int userId) throws BaseException {
        GetUserRes findUser = userService.findById(userId);

        return ResponseEntity
                .ok()
                .body(new BaseResponse<>(findUser));
    }



    /**
     * 회원정보 수정(비밀번호 제외)
     * @param modifyUserReq 값 다 들어있음. 정보수정 창 들어갈때 findById 호출 -> 값 다 갖고 들어가게
     */
    @ApiOperation(value="비밀번호를 제외한 회원 정보 수정")
    @PatchMapping
    public ResponseEntity<?> modifyUser(@RequestBody @Valid ModifyUserReq modifyUserReq) throws BaseException {
        userService.modifyUser(modifyUserReq);

        return ResponseEntity
                .ok()
                .build();
    }

    /**
     * 비밀번호 변경 (비밀번호 찾기로 변경 예정)
     * @param modifyPwdReq
     */
    @ApiOperation(value="비밀번호 수정")
    @PatchMapping("/pwd")
    public ResponseEntity<?> modifyPassword(@RequestBody @Valid ModifyPwdReq modifyPwdReq) throws BaseException {
        userService.modifyPassword(modifyPwdReq);

        return ResponseEntity
                .ok()
                .build();
    }

    /**
     * 회원탈퇴
     * @param userId
     */
    @ApiOperation(value="회원 탈퇴")
    @PostMapping("/delete/{userId}")
    public ResponseEntity<?> deleteUser(@PathVariable @ApiParam(required = true, example = "1") int userId) throws BaseException {
        userService.deleteUser(userId);

        return ResponseEntity
                .ok()
                .build();
    }

//    /**
//     * (test) 회원정보 조회(이메일)
//     */
//    @GetMapping("/byemail/{email}")
//    public ResponseEntity<?> findByEmail(@PathVariable String email) throws BaseException {
//        GetUserRes findUser = userService.findByEmail(email);
//
//        return ResponseEntity
//                .ok()
//                .body(new BaseResponse<>(findUser));
//    }
}
