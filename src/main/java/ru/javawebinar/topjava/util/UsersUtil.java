package ru.javawebinar.topjava.util;

import ru.javawebinar.topjava.model.Role;
import ru.javawebinar.topjava.model.User;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class UsersUtil {
    public static final List<User> users = Arrays.asList(
            new User(1, "1", "1@gmail.com", "111", Role.USER, Role.USER),
            new User(2, "2", "2@gmail.com", "222", Role.USER, Role.USER),
            new User(3, "3", "3@gmail.com", "333", Role.USER, Role.USER),
            new User(4, "4", "4@gmail.com", "444", Role.USER, Role.USER),
            new User(5, "5", "5@gmail.com", "555", Role.ADMIN, Role.ADMIN)
    )
            .stream().sorted((o1, o2) -> o1.getName().compareTo(o2.getName())).collect(Collectors.toList());
}
