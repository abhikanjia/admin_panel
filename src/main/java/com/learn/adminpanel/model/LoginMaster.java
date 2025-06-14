package com.learn.adminpanel.model;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "login_master")
@NoArgsConstructor
@Getter
@Setter
public class LoginMaster {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private int userId;

    @Column(name = "user_is_active")
    private Boolean isActive;

    @Column(name = "user_username", nullable = false, unique = true)
    private String username;

    @Column(name = "user_password", nullable = false)
    private String password;

    @OneToOne
    @JoinColumn(name = "profile_id", referencedColumnName = "profile_id")  // Correct FK reference
    private UserProfile userProfile;

    public LoginMaster(Boolean isActive, String username, String password, UserProfile userProfile) {
        this.isActive = isActive;
        this.username = username;
        this.password = password;
        this.userProfile = userProfile;
    }
}