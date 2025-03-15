package com.learn.adminpanel.model;


import jakarta.persistence.*;

@Entity
@Table(name = "login_master")
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

    public LoginMaster() {}

    public LoginMaster(Boolean isActive, String username, String password, UserProfile userProfile) {
        this.isActive = isActive;
        this.username = username;
        this.password = password;
        this.userProfile = userProfile;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public Boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserProfile getUserProfile() {
        return userProfile;
    }

    public void setUserProfile(UserProfile userProfile) {
        this.userProfile = userProfile;
    }
}