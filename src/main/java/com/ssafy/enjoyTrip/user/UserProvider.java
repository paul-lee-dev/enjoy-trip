package com.ssafy.enjoyTrip.user;

import com.ssafy.enjoyTrip.common.BaseException;
//import com.ssafy.enjoyTrip.user.dao.JdbcTemplateUserDao;
import com.ssafy.enjoyTrip.user.dao.UserDao;
import com.ssafy.enjoyTrip.user.dao.UserMapper;
import com.ssafy.enjoyTrip.user.model.dto.GetUserRes;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.ssafy.enjoyTrip.common.BaseResponseStatus.*;

@Service
public class UserProvider {

    private final UserMapper userDao;

    public UserProvider(UserMapper userDao){
        this.userDao = userDao;
    }

    public GetUserRes findById(int userId) throws BaseException {
        GetUserRes findUser;
        try {
            findUser = userDao.findById(userId);
        } catch (Exception e) {
            e.printStackTrace();
            throw new BaseException(DB_ERROR);
        }

        if (findUser == null) throw new BaseException(WRONG_ID);
        return findUser;
    }

    public GetUserRes findByEmail(String email) throws BaseException {
        GetUserRes findUser;
        try {
            findUser = userDao.findByEmail(email);
        } catch (Exception e) {
            e.printStackTrace();
            throw new BaseException(DB_ERROR);
        }

        if (findUser == null) throw new BaseException(WRONG_ID);
        return findUser;
    }

    public List<GetUserRes> findAll() throws BaseException {
        return userDao.findAll();
    }
}
