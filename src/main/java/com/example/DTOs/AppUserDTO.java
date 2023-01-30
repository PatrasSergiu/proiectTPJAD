package com.example.DTOs;
import com.example.Entities.Course;

import java.io.*;
import java.util.*;

public class AppUserDTO implements Serializable {
    private String username = "";
    private String password = "";
    public int role = 1;
    public Collection<CourseDTO> enrolledCourses = (Collection<CourseDTO>)(new ArrayList<CourseDTO>());
    public Long id = -1L;

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

    public String toString() {
        return "{UserDTO: "+id+" | "+username+" | "+enrolledCourses+"} ";
    }
}