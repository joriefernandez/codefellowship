package com.fernj.lab401.codefellowship;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.CollectionUtils;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
public class FellowshipController {

    @Autowired
    ApplicationUserRepository appUserRepository;

    @Autowired
    PostRepository postRepository;

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
        m.addAttribute("curUser", currentUser);
        m.addAttribute("followings", currentUser.followings);
        return "userprofile";
    }

    @GetMapping("/login")
    public String getLoginPage() {
        return "login";
    }


    //Display all users
    //Ref: https://stackoverflow.com/questions/6416706/easy-way-to-convert-iterable-to-collection
    @GetMapping("/users")
    public String displayAllUsers(Model m, Principal p){
        Iterable<ApplicationUser> allUsers = appUserRepository.findAll();
        List<ApplicationUser> users = new ArrayList<>();

        ApplicationUser currentUser = appUserRepository.findByUsername(p.getName());
        allUsers.forEach(users::add);

        users.remove(currentUser);

        m.addAttribute("currentUser", currentUser);
        m.addAttribute("users", users);
        return "users";

    }

    //Route to view another user
    @GetMapping("/following/{id}")
    public String viewAnotherUser(Model m, Principal p, @PathVariable Long id){
        ApplicationUser currentUser = appUserRepository.findByUsername(p.getName());
        ApplicationUser viewedUser = appUserRepository.findById(id).get();

        m.addAttribute("isFollowed", currentUser.followings.contains(viewedUser));
        m.addAttribute("curUser", currentUser);
        m.addAttribute("otherUser", viewedUser);

        return "anotherUser";
    }

    @PostMapping("/following/{id}")
    public String addAnotherUser(Model m, Principal p, @PathVariable Long id){
        ApplicationUser currentUser = appUserRepository.findByUsername(p.getName());
        ApplicationUser viewedUser = appUserRepository.findById(id).get();

        currentUser.followings.add(viewedUser);
        appUserRepository.save(currentUser);
        return "redirect:/users";
    }


}
