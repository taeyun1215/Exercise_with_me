package dev.ewm.global.OAuth;

import dev.ewm.domain.user.Role;
import dev.ewm.domain.user.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;

@Slf4j
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class OAuthAttributes {
    private Map<String, Object> attributes;
    private String nameAttributeKey;
    private String username;
    private String nickname;
    private String email;
    private Role role;

    public static OAuthAttributes of(
            String registrationId,
            String userNameAttributeName,
            Map<String, Object> attributes
    ) {
        /* 구글인지 네이버인지 카카오인지 구분하기 위한 메소드 (ofNaver, ofKaKao) */
        if ("naver".equals(registrationId)) {
            return ofNaver("id", attributes);
        }
        else if ("kakao".equals(registrationId)){
            return ofKakao("id", attributes);
        }

        return ofGoogle(userNameAttributeName, attributes);
    }

    private static OAuthAttributes ofGoogle(
            String userNameAttributeName,
            Map<String, Object> attributes
    ) {
        String username = (String) attributes.get("email");
        String[] usernameArr = username.split("@");

        return OAuthAttributes.builder()
                .username(usernameArr[0])
                .email((String) attributes.get("email"))
                .nickname((String) attributes.get("name"))
                .attributes(attributes)
                .nameAttributeKey(userNameAttributeName)
                .build();
    }

    private static OAuthAttributes ofNaver(
            String userNameAttributeName,
            Map<String, Object> attributes
    ) {
        /* JSON형태이기 때문에 Map을 통해 데이터를 가져온다. */
        Map<String, Object> response = (Map<String, Object>) attributes.get("response");

        String email = (String) response.get("email");
        String[] username = email.split("@");

        return OAuthAttributes.builder()
                .username(username[0])
                .email(email)
                .nickname((String) response.get("nickname"))
                .attributes(response)
                .nameAttributeKey(userNameAttributeName)
                .build();
    }

    private static OAuthAttributes ofKakao(
            String userNameAttributeName,
            Map<String, Object> attributes
    ) {
        Map<String, Object> kakaoAccount = (Map<String, Object>)attributes.get("kakao_account");
        Map<String, Object> kakaoProfile = (Map<String, Object>)kakaoAccount.get("profile");

        String email = (String) kakaoAccount.get("email");
        String[] username = email.split("@");
        String nickname = (String) kakaoProfile.get("nickname");

        return OAuthAttributes.builder()
                .username(username[0])
                .email(email)
                .nickname(nickname)
                .attributes(attributes)
                .nameAttributeKey(userNameAttributeName)
                .build();
    }

    public User toEntity() {
        return User.builder()
                .username(username)
                .email(email)
                .nickname(nickname)
                .role(Role.SOCIAL)
                .build();
    }
}
