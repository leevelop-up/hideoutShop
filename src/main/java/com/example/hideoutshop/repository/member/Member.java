package com.example.hideoutshop.repository.member;


import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "member")
public class Member {
    @Id @Column(name = "user_id")
    private String userId;

    @Column(name = "user_email")
    private String email;

    @Column(name = "user_password")
    private String password;

    @Column(name = "user_tel")
    private String tel;

    @Column(name = "user_address")
    private String address;

    @Column(name = "user_loginstate")
    private String loginstate;

    @Column(name = "user_joindate")
    private LocalDateTime joindate;

    @Column(name = "user_role", nullable = false)
    @Enumerated(EnumType.STRING)
    private Role role;

}
