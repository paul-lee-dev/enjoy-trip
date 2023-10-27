//package com.ssafy.enjoyTrip.user;
//
//import com.ssafy.enjoyTrip.common.BaseException;
//import com.ssafy.enjoyTrip.user.dao.UserDao;
//import com.ssafy.enjoyTrip.user.model.dto.CreateUserReq;
//import com.ssafy.enjoyTrip.user.model.dto.ModifyPwdReq;
//import com.ssafy.enjoyTrip.user.model.dto.ModifyUserReq;
//import org.springframework.stereotype.Service;
//
//import static com.ssafy.enjoyTrip.common.BaseResponseStatus.*;
//
//@Service
//public class UserService {
//
//    private final UserDao userDao;
//    private final UserProvider userProvider;
//
//    public UserService(UserDao userDao, UserProvider userProvider) {
//        this.userDao = userDao;
//        this.userProvider = userProvider;
//    }
//
//    /**
//     * 회원가입
//     * @param createUserReq
//     * @throws BaseException
//     */
//    public void join(CreateUserReq createUserReq) throws BaseException {
//        // 재입력 비밀번호 체크
//        if (!createUserReq.getPassword().equals(createUserReq.getPasswordCheck()))
//            throw new BaseException(WRONG_PASSCHECK);
//
//        checkUniqueEmail(createUserReq.getEmailId());
//
//        try {
//            if (userDao.createUser(createUserReq) != 1) throw new Exception();
//        } catch (Exception e) {
//            e.printStackTrace();
//            throw new BaseException(DB_ERROR);
//        }
//    }
//
//    public void modifyUser(ModifyUserReq modifyUserReq) throws BaseException {
//        checkUniqueEmail(modifyUserReq.getEmailId());
//        try {
//            userDao.modifyUser(modifyUserReq);
//        } catch (Exception e) {
//            e.printStackTrace();
//            throw new BaseException(DB_ERROR);
//        }
//    }
//
//    public void modifyPassword(ModifyPwdReq modifyPwdReq) throws BaseException {
//        checkRetypePassword(modifyPwdReq.getPassword(), modifyPwdReq.getPasswordCheck());
//        try {
//            userDao.modifyPassword(modifyPwdReq);
//        } catch (Exception e) {
//            e.printStackTrace();
//            throw new BaseException(DB_ERROR);
//        }
//    }
//
//    public void deleteUser(Integer userId) throws BaseException {
//        try {
//            userDao.deleteUser(userId);
//        } catch (Exception e) {
//            e.printStackTrace();
//            throw new BaseException(DB_ERROR);
//        }
//    }
//
//    /**
//     * Email 중복 체크
//     * @param email
//     * @throws BaseException
//     */
//    private void checkUniqueEmail(String email) throws BaseException {
//        if (userProvider.findByEmail(email) != null)
//            throw new BaseException(DUPLICATED_EMAIL);
//    }
//
//    /**
//     * 재입력 비밀번호 체크
//     * @param password
//     * @param passcheck
//     * @throws BaseException
//     */
//    private void checkRetypePassword(String password, String passcheck) throws BaseException {
//        if (!password.equals(passcheck))
//            throw new BaseException(WRONG_PASSCHECK);
//    }
//}
