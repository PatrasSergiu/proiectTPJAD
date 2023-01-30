package com.example.Entities;
import java.io.*;
import java.util.*;
import javax.persistence.*;

@Entity
public class AppUser implements Serializable {
    private String username = "";
    private String password = "";
    private int role = 1;
    private Collection<Course> enrolledCourses = (Collection<Course>)(new ArrayList<Course>());
    private Long id = -1L;
    @Id
    @GeneratedValue
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;

    }

    public void setUsername(String name) {
        this.username = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String pass) {
        this.password = pass;
    }

    public int getRole() {
        return this.role;
    }

    public void setRole(int role) {
        this.role = role;
    }

    @ManyToMany(fetch=FetchType.LAZY)
    public Collection<Course> getEnrolledCourses() {
        return enrolledCourses;
    }

    public void setEnrolledCourses(Collection<Course> courses) {
        this.enrolledCourses = courses;
    }

    public String toString() {
        return "{User: "+id+" | "+username+" | "+enrolledCourses+"} ";
    }
}