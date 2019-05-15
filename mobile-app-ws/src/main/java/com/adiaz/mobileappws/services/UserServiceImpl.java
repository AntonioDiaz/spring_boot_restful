package com.adiaz.mobileappws.services;

import com.adiaz.mobileappws.controller.UserController;
import com.adiaz.mobileappws.model.UserRest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;


@Service
public class UserServiceImpl implements UserService {

    Utils utils;

    public UserServiceImpl() {}

    @Autowired
    public UserServiceImpl(Utils utils) {
        this.utils = utils;
    }

    @Override
    public UserRest createUser(UserRest userRest) {
        String userId = utils.generateUUID();
        userRest.setUserId(userId);
        DataBase.USER_MAP.put(userId, userRest);
        return userRest;
    }
}
