package com.example.Entities;

import javax.persistence.Entity;
import java.io.Serializable;
import java.io.*;
import java.util.*;
import javax.persistence.*;

@Entity
public class Course implements Serializable {
    private Long id = -1L;
    private String title = "";
    private String content = "";
    private float grade = 0;
    private Collection<AppUser> enrolledUsers = (Collection<AppUser>)(new ArrayList<AppUser>());

    @Id
    @GeneratedValue
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public float getGrade() {
        return grade;
    }

    public void setGrade(float grade) {
        this.grade = grade;
    }

    @ManyToMany(fetch=FetchType.LAZY)
    public Collection<AppUser> getEnrolledUsers() {
        return enrolledUsers;
    }

    public void setEnrolledUsers(Collection<AppUser> enrolledUsers) {
        this.enrolledUsers = enrolledUsers;
    }

    public String toString() {
        return "{Course: "+id+" | "+title+" | "+enrolledUsers+"} ";
    }

}
