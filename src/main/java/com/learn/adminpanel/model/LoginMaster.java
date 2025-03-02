package com.learn.adminpanel.model;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "login_master")
public class LoginMaster {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private int userId;

    @Column(name = "user_is_active", nullable = false)
    private Boolean isActive;

    @Column(name = "user_username", nullable = false, unique = true)
    private String username;

    @Column(name = "user_password", nullable = false)
    private String password;

    @OneToOne
    @JoinColumn(name = "profile_id", referencedColumnName = "profile_id")  // Correct FK reference
    private UserProfile userProfile;

    @OneToMany(mappedBy = "loginMaster", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<RoleMaster> roles = new ArrayList<>();

    public LoginMaster() {}

    public LoginMaster(Boolean isActive, String username, String password) {
        this.isActive = isActive;
        this.username = username;
        this.password = password;
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

    public List<RoleMaster> getRoles() {
        return roles;
    }

    public void setRoles(List<RoleMaster> roles) {
        this.roles = roles;
    }

    public void addRole(RoleMaster role) {
        roles.add(role);
        role.setLoginMaster(this);
    }

    public void removeRole(RoleMaster role) {
        roles.remove(role);
        role.setLoginMaster(null);
    }
}