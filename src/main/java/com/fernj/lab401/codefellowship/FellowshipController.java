package com.fernj.lab401.codefellowship;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FellowshipController {
    @GetMapping("/dinosaurs")
    public String getDinosaurs(){
        return "dinosaurs";
    }
}
