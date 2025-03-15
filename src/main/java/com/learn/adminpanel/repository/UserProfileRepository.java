package com.learn.adminpanel.repository;

import com.learn.adminpanel.model.UserProfile;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface UserProfileRepository extends JpaRepository<UserProfile,Integer> {
    @Query("SELECT up FROM UserProfile up LEFT JOIN FETCH up.roles WHERE up.profileId = :id")
    Optional<UserProfile> findByIdWithRoles(@Param("id") int id);

    @Query("SELECT up FROM UserProfile up " +
            "LEFT JOIN FETCH up.roles " +
            "LEFT JOIN FETCH up.loginMaster " +
            "WHERE up.profileId = :id")
    Optional<UserProfile> findByIdWithRolesAndLogin(@Param("id") int id);

    @Modifying
    @Transactional
    @Query("DELETE FROM RoleMaster r WHERE r.userProfile.profileId = :profileId")
    void deleteRolesByProfileId(@Param("profileId") int profileId);
}