package ru.javawebinar.topjava.web.meal;

import org.springframework.stereotype.Controller;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.service.MealService;

import java.util.List;

@Controller
public class MealRestController extends AbstractMealController {
    private MealService service;

    @Override
    public List<Meal> getAll(int userId) {
        return super.getAll(userId);
    }

    @Override
    public Meal get(int id, int userId) {
        return super.get(id, userId);
    }

    @Override
    public Meal create(Meal meal, int userId) {
        return super.create(meal, userId);
    }

    @Override
    public void delete(int id, int userId) {
        super.delete(id, userId);
    }

    @Override
    public void update(Meal meal, int userId) {
        super.update(meal, userId);
    }
}