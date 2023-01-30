package com.example.beans;

import com.example.DTOs.CourseDTO;
import com.example.Entities.AppUser;

import java.io.Serializable;
import java.util.List;

public class BeanJSF implements Serializable {
    private CourseDTO course = null;
    private AppUser appUser = null;
    private List<CourseDTO> courses = null;

    public CourseDTO getCourse() {
        return course;
    }

    public void setCourse(CourseDTO course) {
        this.course = course;
    }

    public AppUser getAppUser() {
        return appUser;
    }

    public void setAppUser(AppUser appUser) {
        this.appUser = appUser;
    }

    public List<CourseDTO> getCourses() {
        return courses;
    }

    public void setCourses(List<CourseDTO> courses) {
        this.courses = courses;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    private long id = -1L;



    public String toString() {
        return "(BeanJSP bean.course="+course+" bean.appUser="+appUser+" bean.courses="+courses+" bean.id="+id+") ";
    }
}
