package com.ssafy.enjoyTrip.user.service;

import com.ssafy.enjoyTrip.common.BaseException;
import com.ssafy.enjoyTrip.user.entity.dto.*;

import javax.servlet.http.HttpSession;
import java.util.List;

public interface UserService {
    void join(CreateUserReq createUserReq) throws BaseException;
    void modifyUser(ModifyUserReq modifyUserReq) throws BaseException;
    void modifyPassword(ModifyPwdReq modifyPwdReq) throws BaseException;
    void deleteUser(Integer userId) throws BaseException;
    void login(LoginReq loginReq, HttpSession session) throws BaseException;
    void logout(HttpSession session) throws BaseException;
    GetUserRes findById(int userId) throws BaseException;
    GetUserRes findByNickname(String email) throws BaseException;
    List<GetUserRes> findAll() throws BaseException;
}
