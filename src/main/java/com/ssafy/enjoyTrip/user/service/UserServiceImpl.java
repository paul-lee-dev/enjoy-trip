package com.ssafy.enjoyTrip.user.service;

import com.ssafy.enjoyTrip.common.BaseException;
import com.ssafy.enjoyTrip.user.dao.UserDao;
import com.ssafy.enjoyTrip.user.entity.User;
import com.ssafy.enjoyTrip.user.entity.dto.*;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.List;

import static com.ssafy.enjoyTrip.common.BaseResponseStatus.*;

@Service
public class UserServiceImpl implements UserService
//        , OAuth2UserService<OAuth2UserRequest, OAuth2User>
{

    private final UserDao userDao;

    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

//    // OAuth
//    @Override
//    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
//        OAuth2UserService<OAuth2UserRequest, OAuth2User> delegate = new DefaultOAuth2UserService();
//        OAuth2User oAuth2User = delegate.loadUser(userRequest);
//
//        String registrationId = userRequest.getClientRegistration().getRegistrationId();
//
//        // 로그인 분류(네이버, 카카오...)
//
//
//    }

    /**
     * 회원가입
     * @param createUserReq
     * @throws BaseException
     */
    public void join(CreateUserReq createUserReq) throws BaseException {
        // 재입력 비밀번호 체크
        if (!createUserReq.getPassword().equals(createUserReq.getPasswordCheck()))
            throw new BaseException(WRONG_PASSCHECK);
        // 이메일 중복 체크
        checkUniqueEmail(createUserReq.getEmailId());

        try {
            userDao.createUser(createUserReq);
        } catch (Exception e) {
            e.printStackTrace();
            throw new BaseException(DB_ERROR);
        }
    }

    /**
     * 로그인 (세션)
     * @param loginReq, session
     * @throws BaseException
     */
    public void login(LoginReq loginReq, HttpSession session) throws BaseException {
        try {
            GetUserRes dbUser = findByEmail(loginReq.getEmailId());
            if (dbUser == null) throw new BaseException(WRONG_EMAIL);
            if (!dbUser.getPassword().equals(loginReq.getPassword())) throw new BaseException(WRONG_PASSWORD);

            session.setAttribute("loginUser", dbUser);
        } catch (Exception e) {
            e.printStackTrace();
            throw new BaseException(DB_ERROR);
        }
    }

    /**
     * 로그아웃(세션)
     * @param session
     * @throws BaseException
     */
    public void logout(HttpSession session) throws BaseException {
        try {
            session.invalidate();
        } catch (Exception e) {
            e.printStackTrace();
            throw new BaseException(SESSION_ERROR);
        }
    }

    public void modifyUser(ModifyUserReq modifyUserReq) throws BaseException {
        checkUniqueEmail(modifyUserReq.getEmailId());
        try {
            userDao.modifyUser(modifyUserReq);
        } catch (Exception e) {
            e.printStackTrace();
            throw new BaseException(DB_ERROR);
        }
    }

    public void modifyPassword(ModifyPwdReq modifyPwdReq) throws BaseException {
        checkRetypePassword(modifyPwdReq.getPassword(), modifyPwdReq.getPasswordCheck());
        try {
            userDao.modifyPassword(modifyPwdReq);
        } catch (Exception e) {
            e.printStackTrace();
            throw new BaseException(DB_ERROR);
        }
    }

    public void deleteUser(Integer userId) throws BaseException {
        try {
            userDao.deleteUser(userId);
        } catch (Exception e) {
            e.printStackTrace();
            throw new BaseException(DB_ERROR);
        }
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

        return findUser;
    }

    public List<GetUserRes> findAll() throws BaseException {
        try {
            return userDao.findAll();
        } catch (Exception e) {
            e.printStackTrace();
            throw new BaseException(DB_ERROR);
        }
    }

    /**
     * Email 중복 체크
     * @param email
     * @throws BaseException
     */
    private void checkUniqueEmail(String email) throws BaseException {
        if (findByEmail(email) != null)
            throw new BaseException(DUPLICATED_EMAIL);
    }

    /**
     * 재입력 비밀번호 체크
     * @param password
     * @param passcheck
     * @throws BaseException
     */
    private void checkRetypePassword(String password, String passcheck) throws BaseException {
        if (!password.equals(passcheck))
            throw new BaseException(WRONG_PASSCHECK);
    }
}
