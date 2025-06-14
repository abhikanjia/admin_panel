package com.learn.adminpanel.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "user_profile")
@NoArgsConstructor
@Getter
@Setter
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

    @OneToOne(mappedBy = "userProfile", cascade = CascadeType.ALL, orphanRemoval = true)
    private LoginMaster loginMaster;

    @OneToMany(mappedBy = "userProfile", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<RoleMaster> roles = new ArrayList<>();

    public UserProfile(String name, String email, byte[] image, LoginMaster loginMaster, List<RoleMaster> roles) {
        this.name = name;
        this.email = email;
        this.image = image;
        this.loginMaster = loginMaster;
        this.roles = roles;
    }

    @Transient
    private List<String> roleNames = new ArrayList<>(); // Initialize here

    public List<String> getRoleNames() {
        return roleNames; // Return direct list
    }

    public void setRoleNames(List<String> roleNames) {
        if (roleNames != null) {
            this.roleNames.clear();
            this.roleNames.addAll(roleNames);
        }
    }

    // Add helper method for roles
    public void addRole(RoleMaster role) {
        role.setUserProfile(this);
        this.roles.add(role);
    }

    public void setLoginMaster(LoginMaster loginMaster) {
        if (this.loginMaster != null) {
            this.loginMaster.setUserProfile(null);
        }
        this.loginMaster = loginMaster;
        if (loginMaster != null) {
            loginMaster.setUserProfile(this);
        }
    }
}