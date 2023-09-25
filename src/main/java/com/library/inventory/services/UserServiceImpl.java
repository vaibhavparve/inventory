package com.library.inventory.services;

import com.google.common.collect.Lists;
import com.library.inventory.exception.ExceptionCodes;
import com.library.inventory.exception.UserNotExists;
import com.library.inventory.models.User;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class UserServiceImpl {
    private final Map<String, User> usersRegister = new ConcurrentHashMap<>();

    //dummy initialization needed some users base for testing.
    // this is definately not prod ready, can be replaced with apis to create users
    UserServiceImpl() {
        this.usersRegister.put("1", new User("1", "tom", Lists.newArrayList()));
        this.usersRegister.put("2", new User("2", "Harry", Lists.newArrayList()));
        this.usersRegister.put("3", new User("3", "Don", Lists.newArrayList()));
    }

    public User getUser(String userId) {
        if (usersRegister.containsKey(userId)) {
            return usersRegister.get(userId);
        } else {
            throw new UserNotExists(HttpStatus.NOT_FOUND, ExceptionCodes.INTERNAL_SERVER_ERROR, "User not found");
        }
    }


    public void updateUser(User user) {
        if (usersRegister.containsKey(user.getUserId())) {
            usersRegister.get(user.getUserId()).setBorrowedItems(user.getBorrowedItems());

        } else {
            throw new UserNotExists(HttpStatus.NOT_FOUND, ExceptionCodes.INTERNAL_SERVER_ERROR, "User not found");
        }
    }
}
