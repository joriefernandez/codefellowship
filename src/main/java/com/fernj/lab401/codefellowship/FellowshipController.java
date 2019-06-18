package com.fernj.lab401.codefellowship;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class FellowshipController {
    @GetMapping("/")
    public String getHomePage(){
        return "info";
    }
}
