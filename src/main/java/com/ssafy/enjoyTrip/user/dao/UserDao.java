package com.ssafy.enjoyTrip.user.dao;

import com.ssafy.enjoyTrip.user.entity.dto.CreateUserReq;
import com.ssafy.enjoyTrip.user.entity.dto.GetUserRes;
import com.ssafy.enjoyTrip.user.entity.dto.ModifyPwdReq;
import com.ssafy.enjoyTrip.user.entity.dto.ModifyUserReq;

import java.util.List;

public interface UserDao {
    void createUser(CreateUserReq createUserReq);
    GetUserRes findById(int userId);
    GetUserRes findByEmail(String email);
    void modifyUser(ModifyUserReq modifyUserReq);
    void modifyPassword(ModifyPwdReq modifyPwdReq);
    void deleteUser(int userId);
    List<GetUserRes> findAll();
}
