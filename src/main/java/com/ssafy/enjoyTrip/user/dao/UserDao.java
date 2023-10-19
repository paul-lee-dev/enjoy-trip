package com.ssafy.enjoyTrip.user.dao;

import com.ssafy.enjoyTrip.user.model.dto.CreateUserReq;
import com.ssafy.enjoyTrip.user.model.dto.GetUserRes;
import com.ssafy.enjoyTrip.user.model.dto.ModifyPwdReq;
import com.ssafy.enjoyTrip.user.model.dto.ModifyUserReq;

public interface UserDao {
    int createUser(CreateUserReq createUserReq);
    GetUserRes findById(int userId);
    GetUserRes findByEmail(String email);
    void modifyUser(ModifyUserReq modifyUserReq);
    void modifyPassword(ModifyPwdReq modifyPwdReq);
    void deleteUser(int userId);
}
