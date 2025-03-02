package com.learn.adminpanel.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity
@Table(name = "user_profile")
public class UserProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "profile_id")
    private int profileId;

    @Column(name = "user_name", nullable = false)
    private String name;

    @Column(name = "user_email", nullable = false, unique = true)
    private String email;

    @Lob
    @Column(name = "user_image", columnDefinition = "BLOB")
    private byte[] image;

    @OneToOne(mappedBy = "userProfile", cascade = CascadeType.ALL)
    private LoginMaster loginMaster;

    public UserProfile() {}

    public UserProfile(String name, String email, byte[] image, LoginMaster loginMaster) {
        this.name = name;
        this.email = email;
        this.image = image;
        this.loginMaster = loginMaster;
    }

    public int getProfileId() {
        return profileId;
    }

    public void setProfileId(int profileId) {
        this.profileId = profileId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public LoginMaster getLoginMaster() {
        return loginMaster;
    }

    public void setLoginMaster(LoginMaster loginMaster) {
        this.loginMaster = loginMaster;
    }
}