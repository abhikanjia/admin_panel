package com.learn.adminpanel.controller;

import com.learn.adminpanel.model.UserProfile;
import com.learn.adminpanel.repository.RoleMasterRepository;
import com.learn.adminpanel.repository.UserProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
public class UserProfileController {


    private UserProfileRepository userProfileRepository;
    private RoleMasterRepository roleMasterRepository;

    @Autowired
    public UserProfileController(UserProfileRepository userProfileRepository , RoleMasterRepository roleMasterRepository){
        this.userProfileRepository = userProfileRepository;
        this.roleMasterRepository = roleMasterRepository;
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
    public String saveEmployee(@ModelAttribute("user") UserProfile theUser){

        //save the employee
        userProfileRepository.save(theUser);

        //use a redirect to prevent duplicate submission
        return "redirect:/allusers";
    }


    @GetMapping("/updateuser")
    public String showFormForUpdate(@RequestParam("profileId") int theId , Model model){

        //create model attribute to bind form data
        Optional<UserProfile> theUser=userProfileRepository.findById(theId);

        model.addAttribute("user" , theUser);

        return "modify-user";
    }




}
