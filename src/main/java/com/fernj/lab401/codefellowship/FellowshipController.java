package com.fernj.lab401.codefellowship;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

import java.security.Principal;
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
    public String createUser(@ModelAttribute ApplicationUser appUser) {

        ApplicationUser newUser = new ApplicationUser(appUser.username, passwordEncoder.encode(appUser.password),
                appUser.firstName, appUser.lastName, appUser.dateOfBirth, appUser.bio);

        appUserRepository.save(newUser);
        ApplicationUser user = appUserRepository.findByUsername(newUser.username);
        Authentication authentication = new UsernamePasswordAuthenticationToken(user, null, new ArrayList<>());
        SecurityContextHolder.getContext().setAuthentication(authentication);
        long id = user.id;
        return "redirect:/users/"+ id;
    }

    @GetMapping("/users/{id}")
    public String getUserInfo(Principal p, Model m) {
        ApplicationUser currentUser = (ApplicationUser)((UsernamePasswordAuthenticationToken) p).getPrincipal();
        m.addAttribute("principal", currentUser);
        return "userprofile";
    }

    @GetMapping("/login")
    public String getLoginPage() {
        return "login";
    }

    @GetMapping("/myprofile")
    public String getLoggedProfile(Principal p, Model m) {
        ApplicationUser currentUser = (ApplicationUser)((UsernamePasswordAuthenticationToken) p).getPrincipal();
        m.addAttribute("principal", currentUser);
        return "userprofile";
    }
}
