package com.ssafy.enjoyTrip.user.service;

import com.ssafy.enjoyTrip.common.BaseException;
import com.ssafy.enjoyTrip.user.dao.UserDao;
import com.ssafy.enjoyTrip.user.entity.User;
import com.ssafy.enjoyTrip.user.entity.dto.*;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.sql.SQLException;
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
        checkRetypePassword(createUserReq.getPassword(), createUserReq.getPasswordCheck());
        // 이메일, 닉네임 중복 체크
        checkUniqueEmail(-1, createUserReq.getEmailId());
        checkUniqueNickname(-1, createUserReq.getNickname());

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
    public void login(LoginReq loginReq, HttpSession session) throws BaseException { // findByEmail에서 예외 맞춰서 throw하므로, 그대로 던지면 됨
        GetUserRes dbUser = findByEmail(loginReq.getEmailId());
        if (dbUser == null) throw new BaseException(WRONG_EMAIL);
        if (!dbUser.getPassword().equals(loginReq.getPassword())) throw new BaseException(WRONG_PASSWORD);

        session.setAttribute("loginUser", dbUser);
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
        GetUserRes findUser = findById(modifyUserReq.getUserId());

        checkUniqueEmail(modifyUserReq.getUserId(), modifyUserReq.getEmailId());
        checkUniqueNickname(modifyUserReq.getUserId(), modifyUserReq.getNickname());
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
        try {
            return userDao.findByEmail(email);
        } catch (Exception e) {
            e.printStackTrace();
            throw new BaseException(DB_ERROR);
        }
    }

    public List<GetUserRes> findAll() throws BaseException {
        try {
            return userDao.findAll();
        } catch (Exception e) {
            e.printStackTrace();
            throw new BaseException(DB_ERROR);
        }
    }

    private GetUserRes findByNickname(String nickname) throws BaseException {
        try {
            return userDao.findByNickname(nickname);
        } catch (Exception e) {
            e.printStackTrace();
            throw new BaseException(DB_ERROR);
        }
    }

    /**
     * Nickname 중복 체크
     * @param nickname
     * @throws BaseException
     */
    private void checkUniqueNickname(int userId, String nickname) throws BaseException {
        GetUserRes sameNicknameUser = findByNickname(nickname);
        if (sameNicknameUser != null && sameNicknameUser.getUserId() != userId)
            throw new BaseException(DUPLICATED_NICKNAME);
    }

    /**
     * Email 중복 체크
     * @param email
     * @throws BaseException
     */
    private void checkUniqueEmail(int userId, String email) throws BaseException {
        GetUserRes sameEmailUser = findByEmail(email);
        if (sameEmailUser != null && sameEmailUser.getUserId() != userId)
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
