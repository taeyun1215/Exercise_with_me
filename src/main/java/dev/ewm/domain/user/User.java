package dev.ewm.domain.user;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import dev.ewm.domain.base.BaseTimeEntity;
import dev.ewm.domain.matePost.MatePost;
import dev.ewm.domain.user.constant.Role;
import lombok.*;
import javax.persistence.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.GenerationType.IDENTITY;

@Getter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User extends BaseTimeEntity implements Serializable {

    @Id @GeneratedValue(strategy = IDENTITY)
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

    @JsonManagedReference
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<MatePost> matePosts = new ArrayList<>();

//    @JsonManagedReference
//    @OneToMany(mappedBy = "user")
//    private List<Mate> mates = new ArrayList<>();

    public String getRoleValue() {
        return this.role.getValue();
    }
}