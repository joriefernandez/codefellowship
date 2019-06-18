package com.fernj.lab401.codefellowship;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

import java.util.ArrayList;
import java.util.Date;

@Controller
public class FellowshipController {

    @Autowired
    ApplicationUserRepository appUserRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;


    @GetMapping("/")
    public String getHomePage(){
        return "info";
    }

    //ApplicationUser(String username, String password, String firstName, String lastName, Date dateOfBirth, String bio)

    @GetMapping("/signup")
    public String getNewUser(Model m){

        m.addAttribute("appUser", new ApplicationUser());
        return "signup";
    }

    @PostMapping("/signup")
    public RedirectView createUser(String username, String password, String firstName, String lastName, Date birthDate, String bio) {

        ApplicationUser newUser = new ApplicationUser(username, passwordEncoder.encode(password), firstName,
                lastName, birthDate, bio);

        appUserRepository.save(newUser);
        Authentication authentication = new UsernamePasswordAuthenticationToken(newUser, null, new ArrayList<>());
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return new RedirectView("/myprofile");
    }

    @GetMapping("/login")
    public String getLoginPage() {
        return "login";
    }
}
