package com.hms.management.usermanagement.controller;

import com.hms.management.usermanagement.domain.User;
import com.hms.management.usermanagement.repository.UserDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.DELETE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

/**
 * UserController will be serving for all rest-full web client request.
 *
 */

@RestController
public class UserController {

    @Autowired
    private UserDao repository;

    private final Logger logger = LoggerFactory.getLogger(this.getClass());


    public void setUserRepository(UserDao userRepository) {
        this.repository = userRepository;
    }

    /**
     * get all the users in the database
     *
     * @return users
     */
    @RequestMapping(value = "/users", method = GET)
    public List<User> getUsers() {
        logger.info("received a get request from client to get user list");
        return repository.findAll();
    }

    /**
     *find a specific user according to the id in the request
     * @param userId is used to identify users uniquely
     * @return User type object
     */
    @RequestMapping(value = "/users/{userId:[\\d]+}", method = GET)
    public void getUser(@PathVariable long userId ){
        logger.info("received a get request from client to get a specific user {} ", userId);
        repository.findById(userId);
    }

    /**
     *create a new user
     * @param user is a new entry to the database
     * @return user type object
     */
    @RequestMapping(value = "/users", method = POST)
    public User createUser(@Valid @RequestBody User user){
        logger.info("received a post request from client to create user");
        repository.add(user);
        return user;
    }

    /**
     *delete a user according to the specific id
     * @param userId used to identify user need to be deleted
     */
    @RequestMapping(value = "/users/{userId:[\\d]+}", method = DELETE)
    public void deleteUser(@PathVariable long userId) {
        logger.info("received a delete request from client to delete a specific user {}", userId);
        repository.deleteById(userId);
    }



}
