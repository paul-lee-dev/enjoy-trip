package com.ssafy.enjoyTrip.user;

import com.ssafy.enjoyTrip.common.BaseException;
import com.ssafy.enjoyTrip.common.BaseResponse;
import com.ssafy.enjoyTrip.user.model.UserDto;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import static com.ssafy.enjoyTrip.common.BaseResponseStatus.FAILED;

/**
 * TODO : validation 자세하게 / 공통 Response, Exception 객체 추가
 */
@Controller
@RequestMapping("/users")
public class UserController {

    private final UserService userService;
    private final UserProvider userProvider;

    public UserController(UserProvider provider, UserService service) {
        this.userProvider = provider;
        this.userService = service;
    }

    /**
     * 회원가입
     * args : String emailId, String password, String phoneNumber
     * return : userId
     */
    @PostMapping
    public BaseResponse<?> join(@RequestBody UserDto userDto) {
        // validation

        if (userDto.getEmailId() == null
                || userDto.getPassword() == null
                || userDto.getNickname() == null)
            return new BaseResponse<>(new BaseException(FAILED));

        try {
            return new BaseResponse<>(userService.join(userDto));
        } catch (Exception e) {
            return new BaseResponse<>(e);
        }
    }

    /**
     * 로그인
     */

    /**
     * 로그아웃
     */

    /**
     * 회원정보 조회
     */
    @GetMapping("/{userId}")
    public BaseResponse<?> findById(@PathVariable Integer userId) {
        // validation
        if (userId == null) return new BaseResponse<>(new BaseException(FAILED));

        try {
            return new BaseResponse<>(userProvider.findById(userId));
        } catch (BaseException e) {
            return new BaseResponse<>(e.getStatus());
        }
    }

    /**
     * 회원탈퇴
     */
    @PostMapping("/delete")
    public BaseResponse<?> deleteUser(@RequestBody Integer userId) {
        // validation
        if (userId == null) return new BaseResponse<>(new BaseException(FAILED));

        try {
            return new BaseResponse<>(userService.deleteUser(userId));
        } catch (BaseException e) {
            return new BaseResponse<>(e.getStatus());
        }
    }

    /**
     * 회원정보 수정
     * @param userDto - 값 다 들어있음. 정보수정 창 들어갈때 findById 호출 -> 값 다 갖고 들어가게
     * @return
     */
    @PostMapping("/update")
    public BaseResponse<?> modifyUser(@RequestBody UserDto userDto) {
        // validation
        if (userDto.getUserId() == null) return new BaseResponse<>(new BaseException(FAILED));

        try {
            return new BaseResponse<>(userService.modifyUser(userDto));
        } catch (BaseException e) {
            return new BaseResponse<>(e.getStatus());
        }
    }
}
