package ru.javawebinar.topjava.util;

import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.model.UserMealWithExcess;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.time.Period;
import java.time.chrono.ChronoLocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

public class UserMealsUtil {
    public static void main(String[] args) {
        List<UserMeal> meals = Arrays.asList(
                new UserMeal(LocalDateTime.of(2020, Month.JANUARY, 30, 10, 0), "Завтрак", 500),
                new UserMeal(LocalDateTime.of(2020, Month.JANUARY, 30, 13, 0), "Обед", 1000),
                new UserMeal(LocalDateTime.of(2020, Month.JANUARY, 30, 20, 0), "Ужин", 500),
                new UserMeal(LocalDateTime.of(2020, Month.JANUARY, 31, 0, 0), "Еда на граничное значение", 100),
                new UserMeal(LocalDateTime.of(2020, Month.JANUARY, 31, 10, 0), "Завтрак", 1000),
                new UserMeal(LocalDateTime.of(2020, Month.JANUARY, 31, 13, 0), "Обед", 500),
                new UserMeal(LocalDateTime.of(2020, Month.JANUARY, 31, 20, 0), "Ужин", 410)
        );

        List<UserMealWithExcess> mealsTo = filteredByCycles(meals, LocalTime.of(7, 0), LocalTime.of(12, 0), 2000);
        mealsTo.forEach(System.out::println);

        List<UserMealWithExcess> mealsTo2 = filteredByStreams(meals, LocalTime.of(7, 0), LocalTime.of(12, 0), 2000);
        mealsTo2.forEach(System.out::println);
    }

    public static List<UserMealWithExcess> filteredByCycles(List<UserMeal> meals, LocalTime startTime, LocalTime endTime, int caloriesPerDay) {
        // TODO return filtered list with excess. Implement by cycles
        Map<String, Integer> map = new HashMap<>();
        for (UserMeal userMeal : meals) {
            if (userMeal.getDateTime().toLocalTime().isAfter(startTime)
                    && userMeal.getDateTime().toLocalTime().isBefore(endTime)) {
                String data = userMeal.getDateTime().format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
                map.merge(data, userMeal.getCalories(), Integer::sum);
            }
        }

        List<UserMealWithExcess> list = new ArrayList<>(meals.size());

        for (UserMeal userMeal : meals) {
            String data = userMeal.getDateTime().format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
            if (map.containsKey(data) && map.get(data) > caloriesPerDay) {
                list.add(new UserMealWithExcess(
                        userMeal.getDateTime(),
                        userMeal.getDescription(),
                        userMeal.getCalories(),
                        true));
            } else {
                list.add(new UserMealWithExcess(
                        userMeal.getDateTime(),
                        userMeal.getDescription(),
                        userMeal.getCalories(),
                        false));
            }
        }
        return list;
    }

    public static List<UserMealWithExcess> filteredByStreams(List<UserMeal> meals, LocalTime startTime, LocalTime endTime, int caloriesPerDay) {
        // TODO Implement by streams
        Map<String, Integer> map = meals.stream()
                .filter(i -> i.getDateTime().toLocalTime().isAfter(startTime))
                .filter(i -> i.getDateTime().toLocalTime().isBefore(endTime))
                .collect(Collectors.toMap(
                        i -> i.getDateTime().format(DateTimeFormatter.ofPattern("dd-MM-yyyy")),
                        UserMeal::getCalories,
                        Integer::sum));

        return meals.stream().map(a -> new UserMealWithExcess(a.getDateTime()
                , a.getDescription()
                , a.getCalories()
                , map.containsKey(a.getDateTime().format(DateTimeFormatter.ofPattern("dd-MM-yyyy")))
                && map.get(a.getDateTime().format(DateTimeFormatter.ofPattern("dd-MM-yyyy"))) > caloriesPerDay))
                .collect(Collectors.toList());
    }
}
