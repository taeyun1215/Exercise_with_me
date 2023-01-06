package dev.ewm.domain.user;

import lombok.*;
import javax.persistence.*;
import javax.validation.constraints.Size;

import static javax.persistence.GenerationType.AUTO;

@Entity
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id @GeneratedValue(strategy = AUTO)
    private Long id;

    @Size(min=2, max=12)
    @Column(nullable = false, unique = true)
    private String nickname;

    @Size(min=5, max=15)
    @Column(nullable = false, unique = true)
    private String username;

    @Size(min=8)
    @Column(nullable = false, unique = true)
    private String password;

    @Size(max=100)
    @Column(nullable = false, unique = true)
    private String email;

}