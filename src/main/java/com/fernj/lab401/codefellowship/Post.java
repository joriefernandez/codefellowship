package com.fernj.lab401.codefellowship;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    long id;

    String body;
    @DateTimeFormat(pattern="yyyy-mm-dd HH:mm:ss")
    Date createdAt;

    @ManyToOne
    ApplicationUser user;

    //Constructors
    public Post(){}

    public Post(String body, ApplicationUser user) {
        this.body = body;
        this.createdAt = new Date();
        this.user = user;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public ApplicationUser getUser() {
        return user;
    }

    public void setUser(ApplicationUser user) {
        this.user = user;
    }
}
