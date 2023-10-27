package com.ssafy.enjoyTrip.user.dao;

import com.ssafy.enjoyTrip.user.model.dto.CreateUserReq;
import com.ssafy.enjoyTrip.user.model.dto.GetUserRes;
import com.ssafy.enjoyTrip.user.model.dto.ModifyPwdReq;
import com.ssafy.enjoyTrip.user.model.dto.ModifyUserReq;

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
