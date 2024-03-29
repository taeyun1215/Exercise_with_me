package user.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.jetbrains.annotations.TestOnly;
import user.adapter.out.persistence.UserJpaEntity;
import user.domain.constant.Role;

import java.io.Serializable;
import java.util.List;

@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class User implements Serializable {

    private Long userId;
    private String username;
    private String password;
    private String nickname;
    private String phone;
    private String email;
    private String address;
    private Role role;

    private List<Long> matePostIds;

    public UserJpaEntity toJpaEntity() {
        return UserJpaEntity.builder()
                .id(userId)
                .username(username)
                .password(password)
                .nickname(nickname)
                .phone(phone)
                .email(email)
                .address(address)
                .role(role)
                .build();
    }

    @TestOnly
    public void updateUserId(Long userId) {
        this.userId = userId;
    }

    public String getRoleValue() {
        return this.role.getValue();
    }

    public void updateUsername(String username) {
        this.username = username;
    }

    public void addMatePosts(Long matePostId) {
        matePostIds.add(matePostId);
    }

}