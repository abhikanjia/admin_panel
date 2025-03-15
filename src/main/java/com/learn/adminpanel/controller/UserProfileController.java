package com.learn.adminpanel.controller;

import com.learn.adminpanel.model.RoleMaster;
import com.learn.adminpanel.model.UserProfile;
import com.learn.adminpanel.repository.UserProfileRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class UserProfileController {


    private UserProfileRepository userProfileRepository;

    @Autowired
    public UserProfileController(UserProfileRepository userProfileRepository){
        this.userProfileRepository = userProfileRepository;
    }

    @GetMapping("/allusers")
    public String AllUsers(Model model){
        List<UserProfile> userList = userProfileRepository.findAll();

        model.addAttribute("users" , userList);

        return "employee-data";
    }

    @PostMapping("/deleteuser")
    public String DeleteUser(@RequestParam("profileId") int theId){
        userProfileRepository.deleteById(theId);
        return "redirect:/allusers";
    }

    @GetMapping("/adduser")
    public String showFormForAdd(Model model){

        //create model attribute to bind form data
        UserProfile theUser = new UserProfile();

        model.addAttribute("user" , theUser);

        return "modify-user";
    }

@PostMapping("/save")
public String saveEmployee(
        @ModelAttribute("user") UserProfile theUser,
        @RequestParam(value = "selectedRoles", required = false) List<String> selectedRoles) {

    // Check if this is an existing user
    boolean isNewUser = theUser.getProfileId() == 0;

    // For existing users: load from DB and update
    if (!isNewUser) {
        UserProfile existingUser = userProfileRepository.findById(theUser.getProfileId())
                .orElseThrow(() -> new EntityNotFoundException("User not found"));

        // Update password only if it's changed
        if (theUser.getLoginMaster() != null && existingUser.getLoginMaster() != null) {
            String newPassword = theUser.getLoginMaster().getPassword();
            if (newPassword != null && !newPassword.isEmpty()) {
                if (!newPassword.startsWith("{noop}")) {
                    existingUser.getLoginMaster().setPassword("{noop}" + newPassword);
                }
            } else {
                // Keep existing password if not modified
                theUser.getLoginMaster().setPassword(existingUser.getLoginMaster().getPassword());
            }
        }

        // Preserve existing relationships
        theUser.setLoginMaster(existingUser.getLoginMaster());
    }

    if (theUser.getLoginMaster() != null) {
        theUser.getLoginMaster().setIsActive(true);
    }

    // Handle roles
    if (!isNewUser) {
        // Clear existing roles only if new roles are selected
        if (selectedRoles != null) {
            userProfileRepository.deleteRolesByProfileId(theUser.getProfileId());
        }
    }

    // Set new roles
    if (selectedRoles != null && !selectedRoles.isEmpty()) {
        List<RoleMaster> roles = selectedRoles.stream()
                .map(roleName -> {
                    RoleMaster role = new RoleMaster();
                    role.setRoleName(roleName);
                    role.setUserProfile(theUser);
                    return role;
                })
                .collect(Collectors.toList());
        theUser.setRoles(roles);
    } else {
        theUser.setRoles(Collections.emptyList());
    }

    // Maintain bidirectional relationship
    if (theUser.getLoginMaster() != null) {
        theUser.getLoginMaster().setUserProfile(theUser);
    }

    userProfileRepository.save(theUser);
    return "redirect:/allusers";
}

    @GetMapping("/updateuser")
    public String showFormForUpdate(@RequestParam("profileId") int theId , Model model){

        // Fetch user with roles and login details
        UserProfile theUser = userProfileRepository.findByIdWithRolesAndLogin(theId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        // Initialize roleNames for Thymeleaf binding
        theUser.setRoleNames(
                theUser.getRoles().stream()
                        .map(RoleMaster::getRoleName)
                        .collect(Collectors.toList())
        );

        model.addAttribute("user" , theUser);
        return "modify-user";
    }
}