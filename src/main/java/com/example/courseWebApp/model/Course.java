package com.example.courseWebApp.model;

import lombok.Data;
import lombok.NonNull;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Document("course")
@Data
public class Course {
    @Id
    private ObjectId id;
    @NonNull
    private String courseName;
    @NonNull
    @Indexed(unique = true)
    private String courseCode;
    @NonNull
    private String courseDescription;
    private List<CourseInstance> courseInstanceList = new ArrayList<>();
}
