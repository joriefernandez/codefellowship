package com.fernj.lab401.codefellowship;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;

@Controller
public class PostController {
    @Autowired
    PostRepository postRepository;

    @Autowired
    ApplicationUserRepository appUserRepository;

    @GetMapping("/myprofile")
    public String getLoggedProfile(Principal p, Model m) {
//        ApplicationUser currentUser = (ApplicationUser)((UsernamePasswordAuthenticationToken) p).getPrincipal();

        //Get logged user info from db
        ApplicationUser me = appUserRepository.findByUsername(p.getName());
        m.addAttribute("curUser", me);

        return "userprofile";
    }
}
