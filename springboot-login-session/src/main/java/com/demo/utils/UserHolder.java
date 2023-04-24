package com.demo.utils;

import com.demo.entity.User;

public class UserHolder {
    private static final ThreadLocal<User> local = new ThreadLocal<>();

    public static void setUser(User user){
        local.set(user);
    }

    public static User getUser(){
        return local.get();
    }

    public static void removeUser(){
        local.remove();
    }
}
