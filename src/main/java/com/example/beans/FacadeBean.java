package com.example.beans;

import com.example.DTOs.AppUserDTO;
import com.example.DTOs.CourseDTO;
import com.example.Entities.AppUser;
import com.example.Entities.Course;
import com.example.interfaces.Facade;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Stateless
@Local(Facade.class)
public class FacadeBean implements Facade {
    @PersistenceContext(unitName = "tpjad")
    private EntityManager manager;


    @Override
    public CourseDTO createCourse(String name, String content) {
        Course course = new Course();
        course.setTitle(name);
        course.setContent(content);
        course.setGrade(0);
        manager.persist(course);
        return course2DTO(course);
    }

    @Override
    public CourseDTO findCourse(Long id) {
        return course2DTO(manager.find(Course.class, id));
    }

    @Override
    public CourseDTO deleteCourse(Long id) {
        Course course = manager.find(Course.class, id);
        CourseDTO courseDTO = course2DTO(course);
        if (course == null) return null;
        for (AppUser user : course.getEnrolledUsers()){
            this.disenrollCourse(appUser2DTO(user), courseDTO);
        } //trb sters cursul din enrolledCourses la fiecare user
        manager.remove(course);
        return courseDTO;
    }

    @Override
    public CourseDTO updateCourse(CourseDTO course) {
        if (course == null) return null;
        Course courseE = manager.find(Course.class, course.id);
        if (courseE == null) return null;
        CourseDTO old = course2DTO(courseE);
        courseE.setGrade(course.grade);
        Collection<AppUser> users = new ArrayList<AppUser>();
        for (AppUserDTO enrUser : course.enrolledUsers) users.add(userDTO2entity(enrUser));
        courseE.setEnrolledUsers(users);
        manager.merge(courseE);
        return old;
    }

    @Override
    public List<CourseDTO> findAllCourses() {
        TypedQuery<Course> query = manager.createQuery(
                "select d from Course d ", Course.class);
        List<CourseDTO> courses = (List<CourseDTO>)(new ArrayList<CourseDTO>());
        for (Course course : query.getResultList()) courses.add(course2DTO(course));
        manager.flush();
        return courses;
    }

    @Override
    public void enrollInCourse(AppUserDTO user, CourseDTO course) {
        course.enrolledUsers.add(user);
        this.updateCourse(course);

        user.enrolledCourses.add(course);
        this.updateUser(user);
    }

    @Override
    public void disenrollCourse(AppUserDTO user, CourseDTO course) {
        course.enrolledUsers.removeIf(x -> x.id == user.id);
        this.updateCourse(course);

        user.enrolledCourses.removeIf(x -> x.id == course.id);
        this.updateUser(user);
    }

    @Override
    public AppUserDTO createUser(String username, String password, int role) {
        AppUser user = new AppUser();
        user.setRole(role);
        user.setUsername(username);
        user.setPassword(password);
        manager.persist(user);
        return appUser2DTO(user);
    }

    @Override
    public AppUserDTO findUser(Long id) {
        return appUser2DTO(manager.find(AppUser.class, id));
    }

    @Override
    public AppUserDTO deleteUser(Long id) {
        AppUser user = manager.find(AppUser.class, id);
        AppUserDTO userDTO = appUser2DTO(user);
        if (user == null) return null;
        for (Course course : user.getEnrolledCourses()){
            this.disenrollCourse(userDTO, course2DTO(course));
        } //trb sters cursul din enrolledCourses la fiecare user
        manager.remove(user);
        return userDTO;
    }

    @Override
    public AppUserDTO updateUser(AppUserDTO user) {
        if (user == null) return null;
        AppUser appUser = manager.find(AppUser.class, user.id);
        if (appUser == null) return null;
        AppUserDTO old = appUser2DTO(appUser);
        Collection<Course> courses = new ArrayList<Course>();
        for (CourseDTO enrCourse : user.enrolledCourses) courses.add(courseDTO2entity(enrCourse));
        appUser.setEnrolledCourses(courses);
        manager.merge(appUser);
        return old;
    }

    @Override
    public List<AppUserDTO> findAllUsers() {
        TypedQuery<AppUser> query = manager.createQuery(
                "select d from AppUser d ", AppUser.class);
        List<AppUserDTO> users = (List<AppUserDTO>)(new ArrayList<AppUserDTO>());
        for (AppUser user : query.getResultList()) users.add(appUser2DTO(user));
        manager.flush();
        return users;
    }



    private CourseDTO course2DTO(Course course) {
        if (course == null) return null;
        CourseDTO courseDTO = new CourseDTO();
        courseDTO.id  = course.getId();
        courseDTO.title = course.getTitle();
        courseDTO.content = course.getContent();
        courseDTO.grade = course.getGrade();
        for (AppUser enrUsers : course.getEnrolledUsers()) courseDTO.enrolledUsers.add(appUser2DTO(enrUsers));
        return courseDTO;
    }
    private AppUserDTO appUser2DTO(AppUser appUser) {
        if (appUser == null) return null;
        AppUserDTO appUserDTO = new AppUserDTO();
        appUserDTO.id = appUser.getId();
        appUserDTO.setUsername(appUser.getUsername());
        appUserDTO.setPassword(appUser.getPassword());
        appUserDTO.role = appUser.getRole();
        for (Course course : appUser.getEnrolledCourses()) appUserDTO.enrolledCourses.add(course2DTO(course));
        return appUserDTO;
    }

    private Course courseDTO2entity(CourseDTO courseDTO) {
        if (courseDTO == null) return null;
        Course course = new Course();
        course.setId(courseDTO.id);
        course.setTitle(courseDTO.title);
        course.setContent(courseDTO.content);
        Collection<AppUser> users = new ArrayList<AppUser>();
        for (AppUserDTO enrUser : courseDTO.enrolledUsers) users.add(userDTO2entity(enrUser));
        return course;
    }

    private AppUser userDTO2entity(AppUserDTO appUserDTO) {
        if (appUserDTO == null) return null;
        AppUser appUser = new AppUser();
        appUser.setId(appUserDTO.id);
        appUser.setRole(appUserDTO.role);
        appUser.setUsername(appUserDTO.getUsername());
        appUser.setPassword(appUserDTO.getPassword());
        Collection<Course> courses = new ArrayList<Course>();
        for (CourseDTO course : appUserDTO.enrolledCourses) courses.add(courseDTO2entity(course));
        appUser.setEnrolledCourses(courses);
        return appUser;
    }
}
