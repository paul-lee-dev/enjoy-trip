package com.ssafy.enjoyTrip.user.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.util.Map;

@ToString
@Getter
@Builder
@AllArgsConstructor
public class OAuthAttributes {
    private String registrationId;
    private Map<String, Object> attributes;
    private String nameAttributeKey;
    private String name;
    private String email;
    private String profileImgUrl;

    public static OAuthAttributes of(String registrationId, String userNameAttributeName,
                                     Map<String, Object> attributes) {
        if (registrationId.equals("google"))
            return ofGoogle(userNameAttributeName, attributes);
        else if (registrationId.equals("kakao"))
            return ofKakao(userNameAttributeName, attributes);
        else
            return ofGoogle(userNameAttributeName, attributes);
    }

    private static OAuthAttributes ofGoogle(String userNameAttributeName, Map<String, Object> attributes) {
        return OAuthAttributes.builder()
                .registrationId("google")
                .attributes(attributes)
                .nameAttributeKey(userNameAttributeName)
                .name((String) attributes.get("name"))
                .email((String) attributes.get("email"))
                .profileImgUrl((String) attributes.get("profileImgUrl"))
                .build();
    }

    private static OAuthAttributes ofKakao(String userNameAttributeName, Map<String, Object> attributes) {
        Map<String, Object> kakaoAccount = (Map<String, Object>) attributes.get("kakao_account");
        Map<String, Object> kakaoProfile = (Map<String, Object>) kakaoAccount.get("profile");

        return OAuthAttributes.builder()
                .registrationId("kakao")
                .attributes(attributes)
                .nameAttributeKey(userNameAttributeName)
                .name((String) kakaoProfile.get("nickname"))
                .profileImgUrl((String) kakaoProfile.get("profile_image_url"))
//                .email((String) kakaoAccount.get("email"))
                .build();
    }

    public GetUserRes toEntity() {
        return GetUserRes.builder()
                .name(name)
                .email(email)
                .role("USER")
                .build();
    }
}
