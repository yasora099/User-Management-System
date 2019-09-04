package com.hms.management.usermanagement.repository;

import com.hms.management.usermanagement.domain.User;

import java.util.List;

/**
 * UserDao interface contains all the abstract methods regarding crud operations related to user
 */
public interface UserDao {

    /**
     * find all the users in the databae in user table
     * @return list of all the users.
     */
    List<User> findAll();

    /**
     * allows to find a specific user by id
     * @param id uses to uniquely identify user
     * @return User type object
     */
    User findById(long id);

    /**
     * delete user from the database
     * @param id can be used to uniquely identify user need to be deleted
     */
    void deleteById(long id);

    /**
     * add user to the database
     * @param user is User type object
     */
    void add(User user);

    /**
     * update the properties of User object
     * @param user is a User type object
     * @return User type updated object
     */
    User update(User user);

}
