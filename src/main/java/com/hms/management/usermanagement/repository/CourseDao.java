package com.hms.management.usermanagement.repository;

import com.hms.management.usermanagement.domain.Course;

import java.util.List;

public interface CourseDao {

    /**
     * find all the courses in the database under course table
     * @return list of course objects
     */
    List<Course> findAll();

    /**
     * find a specific course by using id
     * @param id is used to identify a specific course uniquely
     * @return course object
     */
    Course findById(long id);

    /**
     * create a new course record in the database.
     * @param course is course type object
     */
    void add(Course course);

    /**
     * delete a specific course record using id
     * @param id is ised to uniquely identify the course
     */
    void deleteById(long id);

    /**
     * update a specific course
     * @param course object is passed.
     */
    Course update(Course course);

}
