package com.learn.adminpanel.repository;

import com.learn.adminpanel.model.LoginMaster;
import com.learn.adminpanel.model.RoleMaster;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RoleMasterRepository extends JpaRepository<RoleMaster,Integer> {
    @Query("SELECT DISTINCT r.roleName FROM RoleMaster r")
    List<String> findDistinctRoleNames();
}
