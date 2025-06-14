package com.learn.adminpanel.model;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "role_master")
@NoArgsConstructor
@Getter
@Setter
public class RoleMaster {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "role_id")
    private int roleId;

    @Column(name = "role_rolename", nullable = false)
    private String roleName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "profile_id", referencedColumnName = "profile_id")
    private UserProfile userProfile;

    public RoleMaster(String roleName, UserProfile userProfile) {
        this.roleName = roleName;
        this.userProfile = userProfile;
    }
}