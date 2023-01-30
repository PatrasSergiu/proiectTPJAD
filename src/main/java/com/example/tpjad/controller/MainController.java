package com.example.tpjad.controller;

import com.example.DTOs.CourseDTO;
import com.example.beans.BeanJSF;
import com.example.interfaces.Facade;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.ejb.EJB;
import java.util.List;

@RestController
public class MainController {
    @EJB
    private Facade facade;
    private BeanJSF bean;


    @RequestMapping(value = "/greeting", method = RequestMethod.GET)
    public List<CourseDTO> greet() {
        facade.createCourse("titlu", "continut");
        return facade.findAllCourses();
    }

}
