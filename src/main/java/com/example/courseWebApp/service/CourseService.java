package com.example.courseWebApp.service;

import com.example.courseWebApp.model.Course;
import com.example.courseWebApp.model.CourseInstance;
import com.example.courseWebApp.repository.CourseRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class CourseService{
    @Autowired
    private CourseRepositoryImpl courseRepository;
    // for getting courses that have instance
    public List<Course> getCourseHaveInstance(){
        return courseRepository.getCourseHaveInstance();
    }
    public void createCourse(Course course){
        courseRepository.save(course);
    }

    public List<Course> getAllCourse() {
        return courseRepository.findAll();
    }

    public Course getCourseByCode(String code) {
        return courseRepository.getCourseByCode(code);

    }

    public Course addInstance(Map<String, Object> courseInstance){
        String code = (String) courseInstance.get("code");
        String year = (String) courseInstance.get("year");
        String semester = (String) courseInstance.get("semester");
        Course course=getCourseByCode(code);
        if(course==null) return null;
        CourseInstance instance=new CourseInstance(year, semester);
        course.getCourseInstanceList().add(instance);
        createCourse(course);
        return course;
    }

    public List<Course> getInstances(String year, String semester) {
        List<Course> courses=courseRepository.getInstances(year, semester);
        return courses;
    }

    public Course getCourseInstanceByCode(String year, String semester, String code) {
        Course course=getCourseByCode(code);
        if(course==null) return null;
        for(CourseInstance courseInstance: course.getCourseInstanceList()){
            if(Objects.equals(courseInstance.getYear(), year) && Objects.equals(courseInstance.getSemester(), semester)) return course;
        }

        return null;
    }

    public Course deleteCourseInstanceByCode(String year, String semester, String code){
        Course course=getCourseByCode(code);
        if(course==null) return null;
        List<CourseInstance> courseInstances = course.getCourseInstanceList().stream()
                .filter(x -> !(Objects.equals(x.getSemester(), semester) && Objects.equals(x.getYear(), year)))
                .collect(Collectors.toUnmodifiableList());
        course.setCourseInstanceList(courseInstances);
        createCourse(course);
        return course;
    }

    public Course deleteCourseByCode(String code) {
        Course course=getCourseByCode(code);
        if(course==null) return null;
        courseRepository.deleteCourseByCode(code);
        return course;
    }
}