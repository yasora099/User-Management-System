package com.hms.management.usermanagement.repository;

import com.hms.management.usermanagement.domain.Course;
import com.hms.management.usermanagement.domain.User;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class JpaCourseDaoImpl implements CourseDao{

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public List<Course> findAll() {
        Query query = entityManager.createNativeQuery("SELECT em.* FROM user_details.course as em " , User.class);
        return query.getResultList();
    }

    @Override
    public Course findById(long id) {
        Query q = entityManager.createNativeQuery("SELECT em.* FROM course as em WHERE em.id = :id");
        q.setParameter( "id" , id);
        return (Course) q.getSingleResult();
//        System.out.println( course.toString() );
//        return (Course) course;
    }

    @Override
    public void add(Course course) {
        Query query = entityManager.createNativeQuery("INSERT INTO course (name) VALUES (?)");
        query.setParameter(1, course.getName());
        query.executeUpdate();
    }

    @Override
    public void deleteById(long id) {
        Query query = entityManager.createNativeQuery("DELETE FROM course where id =:id");
        query.setParameter("id", id );
        query.executeUpdate();
    }

    @Override
    public Course update(Course course) {
        return entityManager.merge(course);


    }
}
