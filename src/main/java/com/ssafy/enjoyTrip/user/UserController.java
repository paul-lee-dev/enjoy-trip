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
@CrossOrigin(origins = {"*"})
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
    @PostMapping
    public ResponseEntity<?> join(@RequestBody @Valid CreateUserReq createUserReq) throws BaseException {
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
    @ApiOperation(value="Id를 이용한 회원정보 검색")
    @GetMapping("/{userId}")
    public ResponseEntity<?> findById(@PathVariable @ApiParam(required = true)
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
    @PostMapping("/delete/{userId}")
    public ResponseEntity<?> deleteUser(@PathVariable @ApiParam(required = true) int userId) throws BaseException {
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
