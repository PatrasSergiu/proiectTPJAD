package com.example.interfaces;
import com.example.DTOs.*;

import java.util.List;

public interface Facade {
    public CourseDTO createCourse(String name, String content);
    public CourseDTO findCourse(Long id);
    public CourseDTO deleteCourse(Long id);
    public CourseDTO updateCourse(CourseDTO course);
    public List<CourseDTO> findAllCourses();

    public void enrollInCourse(AppUserDTO user, CourseDTO course);
    public void disenrollCourse(AppUserDTO user, CourseDTO course);

    public AppUserDTO createUser(String username, String password, int role);
    public AppUserDTO findUser(Long id);
    public AppUserDTO deleteUser(Long id);
    public AppUserDTO updateUser(AppUserDTO user);
    public List<AppUserDTO> findAllUsers();
}
