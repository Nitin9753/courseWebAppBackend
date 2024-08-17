package com.example.courseWebApp.controller;

import com.example.courseWebApp.model.Course;
import com.example.courseWebApp.service.CourseService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/courses")
public class CourseController {
    @Autowired
    private CourseService courseService;
    @PostMapping
    public ResponseEntity<?> createCourse(@RequestBody Course course){
        courseService.createCourse(course);
        return new ResponseEntity<>("Course Created" , HttpStatus.OK);
    }
    @GetMapping
    public ResponseEntity<?> getAllCourse(){
        List<Course> courses=courseService.getAllCourse();
        return new ResponseEntity<>(courses, HttpStatus.OK);
    }

    @GetMapping("/{code}")
    public ResponseEntity<?> getCourseByCode(@PathVariable String code){
        Course courses=courseService.getCourseByCode(code);
        return new ResponseEntity<>(courses, HttpStatus.OK);
    }

    @DeleteMapping("/{code}")
    public ResponseEntity<?> deleteCourseByCode(@PathVariable String code){
        Course course =courseService.deleteCourseByCode(code);
        return new ResponseEntity<>(course, HttpStatus.OK);
    }
}
