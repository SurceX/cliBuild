package com.cesur.user;

import java.util.UUID;

public class UserService {

    private final UserDao userDao = new UserDao();


    public User[] getUsers() {
        return userDao.getUsers();
    }


    public User findUserById (UUID userId){
        return userDao.findUserById(userId);
    }
}
