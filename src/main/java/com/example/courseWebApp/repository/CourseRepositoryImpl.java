package com.example.courseWebApp.repository;

import com.example.courseWebApp.model.Course;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public class CourseRepositoryImpl {
    @Autowired
    private MongoTemplate mt;
    public Course getCourseByCode(String code){
        Query query=new Query();
        query.addCriteria(Criteria.where("courseCode").is(code));
        Course course=mt.findOne(query, Course.class);
        return course;
    }
    public void save(Course course){
        mt.save(course);
    }

    public List<Course> findAll() {
        return mt.findAll(Course.class);
    }


    public List<Course> getInstances(String year, String semester) {
        Query query=new Query();
        query.addCriteria(Criteria.where("courseInstanceList.year").is(year));
        query.addCriteria(Criteria.where("courseInstanceList.semester").is(semester));
//        mt.updateFirst(query, )
        List<Course> courses=mt.find(query, Course.class);
        return courses;
    }

    public void deleteCourseByCode(String code) {
        Query query=new Query();
        query.addCriteria(Criteria.where("courseCode").is(code));
        mt.findAndRemove(query, Course.class);
        return ;
    }

    public List<Course> getCourseHaveInstance(){
        Query query=new Query();
        query.addCriteria(Criteria.where("courseInstanceList").exists(true).not().size(0));
        return mt.find(query, Course.class);
    }
}
