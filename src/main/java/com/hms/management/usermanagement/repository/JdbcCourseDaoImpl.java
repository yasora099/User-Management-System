package com.hms.management.usermanagement.repository;

import com.hms.management.usermanagement.domain.Course;
import com.hms.management.usermanagement.domain.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * JdbcCourseDaoImpl implements all the abstract methods in CourseDao interface
 */
//@Repository
public class JdbcCourseDaoImpl implements CourseDao {

    private JdbcTemplate jdbctemplate;

    @Override
    public List<Course> findAll() {
        return jdbctemplate.query("select * from course", new JdbcCourseDaoImpl.CourseRowMapper());
    }

    @Override
    public Course findById(long id) {
        return jdbctemplate.queryForObject("select * from course where id=?", new Object[] { id },
                new CourseRowMapper());
    }

    @Override
    public void add(Course course) {
        jdbctemplate.update("insert into course (id, name) " + "values(?,  ?)", course.getId(),
                course.getName());
    }

    @Override
    public void deleteById(long id) {
        int status = jdbctemplate.update("delete from course where id=?", id );
        if (status != 0) {
            System.out.println(" data deleted for ID " + id);
        } else {
            System.out.println( "No user found with ID " + id );
        }
    }

    @Override
    public Course update(Course course) {
        jdbctemplate.update("update course " + " set name = ? " + " where id = ?", course.getName());
        return course;
    }

    class CourseRowMapper implements RowMapper<Course> {
        @Override
        public Course mapRow(ResultSet rs, int rowNum) throws SQLException {
            Course course = new Course();
            course.setId( rs.getLong( "id" ) );
            course.setName(rs.getString("name"));
            return course;
        }
    }
}
