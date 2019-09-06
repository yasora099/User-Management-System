package com.hms.management.usermanagement.controller;

import com.hms.management.usermanagement.domain.Course;
import com.hms.management.usermanagement.repository.CourseDao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.DELETE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@Repository
public class CourseController {

    @Autowired
    CourseDao repository;

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @RequestMapping(value = "/courses", method = GET)
    public List<Course> getCourses() {
        logger.info("received a get request from client to get courses list");
        return repository.findAll();
    }

    @RequestMapping(value = "/courses/{courseId:[\\d]+}", method = GET)
    public void getCourse(@PathVariable long courseId ){
        logger.info("received a get request from client to get a specific course {} ", courseId);
        repository.findById(courseId);
    }

    @RequestMapping(value = "/courses", method = POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Course createCourse(@Valid @RequestBody Course course){
        logger.info("received a post request from client to create course");
        repository.add(course);
        return course;
    }

    @RequestMapping(value = "/courses/{courseId:[\\d]+}", method = DELETE)
    public void deleteCourse(@PathVariable long courseId) {
        logger.info("received a delete request from client to delete a specific course {}", courseId);
        repository.deleteById(courseId);
    }

}
