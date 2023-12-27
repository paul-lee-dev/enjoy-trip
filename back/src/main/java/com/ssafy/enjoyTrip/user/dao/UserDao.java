package com.ssafy.enjoyTrip.user.dao;

import com.ssafy.enjoyTrip.user.entity.dto.*;

import java.util.List;

public interface UserDao {
    void createUser(CreateUserReq createUserReq);
    void createSnsUser(SnsInfoDto snsInfoDto);
    GetUserRes findById(int userId);
    GetUserRes findByEmail(String email);
    GetUserRes findByNickname(String nickname);
    void modifyUser(ModifyUserReq modifyUserReq);
    void modifyPassword(ModifyPwdReq modifyPwdReq);
    void deleteUser(int userId);
    List<GetUserRes> findAll();
}
