package com.learn.adminpanel.model;

import jakarta.persistence.*;

@Entity
@Table(name = "role_master")
public class RoleMaster {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "role_id")
    private int roleId;

    @Column(name = "role_rolename", nullable = false)
    private String roleName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "profile_id", referencedColumnName = "profile_id")
    private LoginMaster loginMaster;

    public RoleMaster() {}

    public RoleMaster(String roleName, LoginMaster loginMaster) {
        this.roleName = roleName;
        this.loginMaster = loginMaster;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public LoginMaster getLoginMaster() {
        return loginMaster;
    }

    public void setLoginMaster(LoginMaster loginMaster) {
        this.loginMaster = loginMaster;
    }
}