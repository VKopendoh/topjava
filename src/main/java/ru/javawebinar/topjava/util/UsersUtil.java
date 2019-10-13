package ru.javawebinar.topjava.util;

import ru.javawebinar.topjava.model.Role;
import ru.javawebinar.topjava.model.User;

import java.util.Arrays;
import java.util.List;

public class UsersUtil {
    public static final List<User> USERS = Arrays.asList(
            new User(null, "Vasya", "mail@neru", "pass1", Role.ROLE_USER),
            new User(null, "Ivan", "gmail@ru", "pass2", Role.ROLE_USER)
    );

}
