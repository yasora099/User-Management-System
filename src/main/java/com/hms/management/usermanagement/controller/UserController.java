package com.hms.management.usermanagement.controller;

import com.hms.management.usermanagement.domain.User;
import com.hms.management.usermanagement.repository.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * UserController will be serving for all rest-full web client request.
 *
 */

@RestController
public class UserController {

    @Autowired
    private UserDao repository;

    public void setUserRepository(UserDao userRepository) {
        this.repository = userRepository;
    }

    /**
     *get all the users in the database
     * @return users
     */
    @GetMapping(value = "/users")
    public List<User> getUsers() {
        return repository.findAll();
    }

    /**
     *find a specific user according to the id in the request
     * @param userId is used to identify users uniquely
     * @return User type object
     */
    @GetMapping(value = "/user/{id}")
    public User getUser(@PathVariable(value = "id") long userId) {
        return repository.findById(userId);
    }

    /**
     *create a new user
     * @param user is a new entry to the database
     * @return user type object
     */
    @PostMapping(value = "/users")
    public User createUser(@Valid @RequestBody User user){
        repository.add(user);
        return user;
    }

    /**
     *delete a user according to the specific id
     * @param id is used to identify user need to be deleted
     */
    @DeleteMapping("/users/{id}" )
    public void deleteUser(@RequestParam(value = "userId", defaultValue = " ") long id) {
        repository.deleteById(id);
    }



}
