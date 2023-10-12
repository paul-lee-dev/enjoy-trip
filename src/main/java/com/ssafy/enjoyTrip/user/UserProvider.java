package com.ssafy.enjoyTrip.user;

import com.ssafy.enjoyTrip.common.BaseException;
import com.ssafy.enjoyTrip.user.model.UserDto;
import org.springframework.stereotype.Service;

import static com.ssafy.enjoyTrip.common.BaseResponseStatus.DATABASE_ERROR;
import static com.ssafy.enjoyTrip.common.BaseResponseStatus.FAILED;

@Service
public class UserProvider {

    private final UserDao userDao;

    public UserProvider(UserDao userDao){
        this.userDao = userDao;
    }

    public UserDto findById(Integer userId) throws BaseException {
        UserDto findUser;
        try {
            findUser = userDao.findById(userId);
        } catch (Exception e) {
            e.printStackTrace();
            throw new BaseException(DATABASE_ERROR);
        }
        if (findUser == null) throw new BaseException(FAILED);
        return findUser;
    }
}
