package com.adiaz.mobileappws.controller;

import com.adiaz.mobileappws.exceptions.UserServiceException;
import com.adiaz.mobileappws.model.UserRest;
import com.adiaz.mobileappws.model.UserRestUpdate;
import com.adiaz.mobileappws.services.DataBase;
import com.adiaz.mobileappws.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/users")
public class UserController {

    //public static Map<String, UserRest> usersMap = new HashMap<>();

    @Autowired
    UserService userService;

    @GetMapping(path = "/{userId}", produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<UserRest> get(@PathVariable String userId) {
        /**
        String firstName = null;
        int lenght = firstName.length();
        if (true)
            throw new UserServiceException("A user service exception is thrown.");
         */
        if (DataBase.USER_MAP.containsKey(userId))
            return new ResponseEntity<>(DataBase.USER_MAP.get(userId), HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping
    public String get(@RequestParam(name = "page") Integer page, @RequestParam ( defaultValue = "69")Integer limit) {
        return "GET: page " + page + " limit " + limit;
    }

    @PostMapping(
            consumes = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<UserRest> create(@Valid @RequestBody UserRest userRest) {
        UserRest newUser = userService.createUser(userRest);
        return new ResponseEntity<>(newUser, HttpStatus.OK);
    }

    @PutMapping(
            path = "/{userId}",
            consumes = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<UserRest> update(@PathVariable String userId, @Valid @RequestBody UserRestUpdate userRestUpdate) {
        if(!DataBase.USER_MAP.containsKey(userId))
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        UserRest userRest = DataBase.USER_MAP.get(userId);
        userRest.setFirstName(userRestUpdate.getFirstName());
        userRest.setLastName(userRestUpdate.getLastName());
        return new ResponseEntity<>(userRest, HttpStatus.OK);
    }

    @DeleteMapping(path = "/{userId}")
    public ResponseEntity<Void> delete(@PathVariable String userId) {
        DataBase.USER_MAP.remove(userId);
        return ResponseEntity.noContent().build();
    }
}
