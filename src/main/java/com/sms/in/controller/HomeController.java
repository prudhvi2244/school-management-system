package com.sms.in.controller;

import com.sms.in.entity.UserRegistration;
import com.sms.in.service.IUserRegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import java.util.Arrays;
import java.util.List;

@Controller
public class HomeController {

    @Autowired
    private IUserRegistrationService uservice;

    @GetMapping(value = "/")
    public String homePage()
    {
        return "home";
    }
    @GetMapping(value = "/about")
    public String aboutPage()
    {
        return "about";
    }

    @GetMapping(value = "/admissions")
    public String admissionsPage()
    {
        return "admissions";
    }

    @GetMapping(value = "/login")
    public String loginPage()
    {
        return "login";
    }

    @GetMapping(value = "/register")
    public String getUserRegistrationPage(Model model)
    {
        List<String> cities=Arrays.asList("Bangalore","Mumbai","Chennai");
        List<String> states=Arrays.asList("Andhra Pradesh","Maharastra","Tamilnadu");
        UserRegistration user1=new UserRegistration();
        model.addAttribute("user1",user1);
        model.addAttribute("cities",cities);
        model.addAttribute("states",states);
        return "register";
    }

    @PostMapping(value = "/register")
    public String postUserRegistrationPage(@Valid @ModelAttribute("user1") UserRegistration user1, Errors errors, Model model)
    {
        if(errors.hasErrors())
        {

            return "register";
        }
        else
        {
            UserRegistration userRegistration=uservice.saveUser(user1);
            model.addAttribute("msg","Registration Success!");
            return "register";
        }

    }


}
