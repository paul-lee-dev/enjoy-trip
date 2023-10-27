package com.ssafy.enjoyTrip.user;

import com.ssafy.enjoyTrip.common.BaseException;
import com.ssafy.enjoyTrip.common.BaseResponse;
import com.ssafy.enjoyTrip.user.model.dto.CreateUserReq;
import com.ssafy.enjoyTrip.user.model.dto.GetUserRes;
import com.ssafy.enjoyTrip.user.model.dto.ModifyPwdReq;
import com.ssafy.enjoyTrip.user.model.dto.ModifyUserReq;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * TODO : 소셜 로그인/로그아웃, 토큰 등 세션유지
 */
@Controller
@RequestMapping("/users")
public class UserController {

//    private final UserService userService;
    private final UserProvider userProvider;

//    public UserController(UserProvider provider, UserService service) {
    public UserController(UserProvider provider) {
        this.userProvider = provider;
//        this.userService = service;
    }

//    /**
//     * 회원가입
//     * @param createUserReq
//     */
//    @PostMapping
//    public ResponseEntity<?> join(@RequestBody @Valid CreateUserReq createUserReq) throws BaseException {
//        userService.join(createUserReq);
//
//        return new ResponseEntity<>(HttpStatus.OK);
//    }

    /**
     * 회원 리스트 조회
     * @return
     * @throws BaseException
     */
    @GetMapping
    public ResponseEntity<?> findAll() throws BaseException {
        return ResponseEntity
                .ok()
                .body(userProvider.findAll());
    }

    /**
     * 로그인
     */

    /**
     * 로그아웃
     */

    /**
     * 회원정보 조회
     * @param userId
     */
    @GetMapping("/{userId}")
    public ResponseEntity<?> findById(@PathVariable int userId) throws BaseException {
        GetUserRes findUser = userProvider.findById(userId);

        return ResponseEntity
                .ok()
                .body(new BaseResponse<>(findUser));
    }


//    /**
//     * 회원정보 수정(비밀번호 제외)
//     * @param modifyUserReq 값 다 들어있음. 정보수정 창 들어갈때 findById 호출 -> 값 다 갖고 들어가게
//     */
//    @PatchMapping
//    public ResponseEntity<?> modifyUser(@RequestBody @Valid ModifyUserReq modifyUserReq) throws BaseException {
//        userService.modifyUser(modifyUserReq);
//
//        return new ResponseEntity<>(HttpStatus.OK);
//    }
//
//    /**
//     * 비밀번호 변경
//     * @param modifyPwdReq
//     */
//    @PatchMapping
//    public ResponseEntity<?> modifyPassword(@RequestBody @Valid ModifyPwdReq modifyPwdReq) throws BaseException {
//        userService.modifyPassword(modifyPwdReq);
//
//        return new ResponseEntity<>(HttpStatus.OK);
//    }
//
//    /**
//     * 회원탈퇴
//     * @param userId
//     */
//    @PostMapping("/delete")
//    public ResponseEntity<?> deleteUser(@RequestBody int userId) throws BaseException {
//        userService.deleteUser(userId);
//
//        return new ResponseEntity<>(HttpStatus.OK);
//    }
}
