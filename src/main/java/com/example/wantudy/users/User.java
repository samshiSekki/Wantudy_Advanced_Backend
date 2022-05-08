package com.example.wantudy.users;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "users")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Id
    @Column(name = "user_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @Column(name = "email", length = 50, unique = true)
    private String email;

    @Column(name = "password", length = 100)
    private String password;

    @Column
    private String profileImage;

    @Column
    private String nickname;

    @Column
    private Boolean applicationState;

    @Column
    private Boolean profileState;

    @Column(length=50)
    private String selfIntroduction;

    @Column
    private String provider;

}