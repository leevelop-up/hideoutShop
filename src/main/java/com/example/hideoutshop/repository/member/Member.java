package com.example.hideoutshop.repository.member;


import lombok.*;

import javax.persistence.*;

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


}
