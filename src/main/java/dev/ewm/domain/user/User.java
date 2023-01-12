package dev.ewm.domain.user;

import dev.ewm.domain.user.constant.Role;
import lombok.*;
import javax.persistence.*;

import static javax.persistence.GenerationType.AUTO;

@Getter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id @GeneratedValue(strategy = AUTO)
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;

    private String password;

    @Column(nullable = false, unique = true)
    private String nickname;

    @Column(unique = true)
    private String phone;

    @Column(nullable = false, unique = true)
    private String email;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

    public String getRoleValue() {
        return this.role.getValue();
    }
}