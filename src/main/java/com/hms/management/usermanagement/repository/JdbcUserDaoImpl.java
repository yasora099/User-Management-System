package com.hms.management.usermanagement.repository;

import com.hms.management.usermanagement.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * JdbcUserDaoImpl implements all the abstract methods in UserDao interface
 */
//@Repository
public class JdbcUserDaoImpl implements UserDao {


    JdbcTemplate jdbcTemplate;

    public List<User> findAll() {
        return jdbcTemplate.query("select * from user", new UserRowMapper());
    }

    public User findById(long id) {
         return jdbcTemplate.queryForObject("select * from user where id=?", new Object[] { id },
                new UserRowMapper());
    }

    public void deleteById(long id) {
        int status = jdbcTemplate.update("delete from user where id=?", id );
        if (status != 0) {
            System.out.println(" data deleted for ID " + id);
        } else {
            System.out.println( "No user found with ID " + id );
        }
    }

    public void add(User user) {
        jdbcTemplate.update("insert into user (id, name, email) " + "values(?,  ?, ?)", user.getId(),
                user.getName(), user.getEmail() );

    }

    public User update(User user) {
        jdbcTemplate.update("update user " + " set name = ?, email = ? " + " where id = ?",
                user.getName(), user.getEmail() );
        return user;
    }

    class UserRowMapper implements RowMapper<User> {
        @Override
        public User mapRow(ResultSet rs, int rowNum) throws SQLException {
            User user = new User();
            user.setId(rs.getInt("id"));
            user.setName(rs.getString("name"));
            user.setEmail(rs.getString("email"));
            return user;
        }
    }

}
