package com.example.DTOs;

import com.example.Entities.AppUser;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

public class CourseDTO implements Serializable {
    public Long id = -1L;
    public String title = "";
    public String content = "";
    public float grade = 0;
    public Collection<AppUserDTO> enrolledUsers = (Collection<AppUserDTO>)(new ArrayList<AppUserDTO>());


    public String toString() {
        return "{Course: "+id+" | "+title+" | "+enrolledUsers+"} ";
    }

}