package com.ssafy.enjoyTrip.user;

import com.ssafy.enjoyTrip.common.BaseException;
import com.ssafy.enjoyTrip.user.model.UserDto;
import org.springframework.stereotype.Service;

import static com.ssafy.enjoyTrip.common.BaseResponseStatus.DATABASE_ERROR;
import static com.ssafy.enjoyTrip.common.BaseResponseStatus.FAILED;

@Service
public class UserService {

    private final UserDao userDao;
    private final UserProvider userProvider;

    public UserService(UserDao userDao, UserProvider userProvider) {
        this.userDao = userDao;
        this.userProvider = userProvider;
    }

    /**
     * 회원가입
     * TODO : password 형식 validation
     * @param userDto
     * @return userId
     * @throws Exception
     */
    public int join(UserDto userDto) throws BaseException {
        // validation
        if (userProvider.findById(userDto.getUserId()) != null)
            throw new BaseException(FAILED);

        // possword validation 추가하기

        try {
            return userDao.createUser(userDto);
        } catch (Exception e) {
            e.printStackTrace();
            throw new BaseException(DATABASE_ERROR);
        }
    }

    public int deleteUser(Integer userId) throws BaseException {
        // validation
        if (userProvider.findById(userId) != null)
            throw new BaseException(FAILED);

        try {
            return userDao.deleteUser(userId);
        } catch (Exception e) {
            e.printStackTrace();
            throw new BaseException(DATABASE_ERROR);
        }
    }

    public int modifyUser(UserDto userDto) throws BaseException {
        // validation
        if (userDao.findById(userDto.getUserId()) == null)
            throw new BaseException(FAILED);
        try {
            return userDao.modifyUser(userDto);
        } catch (Exception e) {
            e.printStackTrace();
            throw new BaseException(DATABASE_ERROR);
        }
    }
}
