package com.fernj.lab401.codefellowship;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class AppUser {

    //Make the encrypter static so it has its own instance
    @Autowired
    static BCryptPasswordEncoder bCryptPasswordEncoder;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    long id;

    String username;
    String password;

    public AppUser() {
    }

    public AppUser(String username, String password) {
        this.username = username;
        this.password = bCryptPasswordEncoder.encode(password);
    }
}
