package com.example.courseWebApp.controller;


import com.example.courseWebApp.model.Course;
import com.example.courseWebApp.model.CourseInstance;
import com.example.courseWebApp.service.CourseService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/instances")
public class InstanceController {
    @Autowired
    private CourseService courseService;

    @GetMapping
    public ResponseEntity<?> getCourses(){
        List<Course> newCourse=courseService.getCourseHaveInstance();
        return new ResponseEntity<>(newCourse, HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<?> createInstance(@RequestBody Map<String, Object> courseInstance){
        Course newCourse=courseService.addInstance(courseInstance);
        return new ResponseEntity<>(newCourse, HttpStatus.OK);
    }

    @GetMapping("{year}/{semester}")
    public ResponseEntity<?> getInstances(@PathVariable String year, @PathVariable String semester){
        List<Course> courses=courseService.getInstances(year, semester);
        return new ResponseEntity<>(courses, HttpStatus.OK);
    }

    @GetMapping("{year}/{semester}/{code}")
    public ResponseEntity<?> getInstanceCourseDetail(@PathVariable String year, @PathVariable String semester, @PathVariable String code){
        System.out.print("the valu eof th three values are: " + year+ semester+code);
        Course course=courseService.getCourseInstanceByCode(year, semester, code);
        return new ResponseEntity<>(course, HttpStatus.OK);
    }
    @DeleteMapping("{year}/{semester}/{code}")
    public ResponseEntity<?> deleteCourseInstanceByCode(@PathVariable String year, @PathVariable String semester, @PathVariable String code){
        Course course=courseService.deleteCourseInstanceByCode(year, semester, code);
        return new ResponseEntity<>(course, HttpStatus.OK);
    }


}
