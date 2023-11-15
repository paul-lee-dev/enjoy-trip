package com.ssafy.enjoyTrip.user.service;

import com.ssafy.enjoyTrip.common.BaseException;
import com.ssafy.enjoyTrip.user.dao.UserDao;
import com.ssafy.enjoyTrip.user.entity.dto.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AccessToken;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpSession;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.ssafy.enjoyTrip.common.BaseResponseStatus.*;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService, OAuth2UserService<OAuth2UserRequest, OAuth2User> {

    private final UserDao userDao;
    private final HttpSession httpSession;

    private final String googleUserInfoUrl = "https://www.googleapis.com/userinfo/v2/me";

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2UserService<OAuth2UserRequest, OAuth2User> delegate = new DefaultOAuth2UserService();
        OAuth2User oAuth2User = delegate.loadUser(userRequest);

        String registrationId = userRequest.getClientRegistration().getRegistrationId();
        String userNameAttributeName = userRequest.getClientRegistration().getProviderDetails()
                .getUserInfoEndpoint().getUserNameAttributeName();
        String accessTokenValue = userRequest.getAccessToken().getTokenValue();

        OAuthAttributes attributes = OAuthAttributes.of(registrationId, userNameAttributeName, oAuth2User.getAttributes());

        try {
            GetUserRes loginUser = saveOrUpdate(attributes,accessTokenValue);

            httpSession.setAttribute("loginUser", loginUser);

            return new DefaultOAuth2User(
                    Collections
                            .singleton(
                            new SimpleGrantedAuthority(
                                    "ROLE_"+loginUser
                                            .getRole())),
                    attributes.getAttributes(),
                    attributes.getNameAttributeKey());
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("소셜 로그인 - saveOrUpdate 중 오류");
        }
        return null;
    }

    private GetUserRes saveOrUpdate(OAuthAttributes attributes, String accessTokenValue) throws Exception {
        GetUserRes getUserRes = findByEmail(attributes.getEmail());
        Map<String, String> newInfo = getNewestUserInfo(accessTokenValue);
        String name = newInfo.get("name");
        String email = newInfo.get("email");
        String profileImgUrl = newInfo.get("picture");
        if (getUserRes != null) {
            System.out.println("user 이미 있음");
            // 수정 필요
            modifyUser(ModifyUserReq.builder()
                    .userId(getUserRes.getUserId())
                    .name(name)
                    .email(email)
                    .profileImgUrl(profileImgUrl)
                    .phoneNumber(getUserRes.getPhoneNumber())
                    .nickname(getUserRes.getNickname())
                    .build());
            System.out.println("modify userInfo");
        } else {
            System.out.println("새 유저 생성");
            userDao.createSnsUser(SnsInfoDto.builder()
                    .email(email)
                    .name(name)
                    .profileImgUrl(profileImgUrl)
                    .snsType("google")
                    .build());
            System.out.println("새 유저 생성 완료");
        }
        return findByEmail(attributes.getEmail());
    }

    private Map<String, String> getNewestUserInfo(String accessTokenValue) {
        HttpHeaders headers = new HttpHeaders();
        RestTemplate restTemplate = new RestTemplate();

        // 헤더 설정
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        headers.set("Authorization", "Bearer "+ accessTokenValue);

        HttpEntity<?> userInfoEntity = new HttpEntity<>(headers);

        ResponseEntity<HashMap> userInfoRes = restTemplate.exchange(googleUserInfoUrl, HttpMethod.GET,
                userInfoEntity, HashMap.class);
        return userInfoRes.getBody();
    }


    /**
     * 회원가입
     * @param createUserReq
     * @throws BaseException
     */
    public void join(CreateUserReq createUserReq) throws BaseException {
        // 재입력 비밀번호 체크
        checkRetypePassword(createUserReq.getPassword(), createUserReq.getPasswordCheck());
        // 이메일, 닉네임 중복 체크
        checkUniqueEmail(-1, createUserReq.getEmail());
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
        GetUserRes dbUser = findByEmail(loginReq.getEmail());
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

        checkUniqueEmail(modifyUserReq.getUserId(), modifyUserReq.getEmail());
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
