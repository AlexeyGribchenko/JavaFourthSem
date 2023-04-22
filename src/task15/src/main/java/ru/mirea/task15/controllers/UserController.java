package ru.mirea.task15.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import ru.mirea.task15.entities.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.mirea.task15.repos.UserRepo;

import java.util.ArrayList;

@Controller
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserRepo userRepo;

    @GetMapping("/add")
    public String add(
            @RequestParam(required = false, defaultValue = "Иван") String firstName,
            @RequestParam(required = false, defaultValue = "Иванов") String middleName,
            @RequestParam(required = false, defaultValue = "Иванович") String lastName,
            @RequestParam(required = false, defaultValue = "01.01.2000") String birthDate,
            Model model
    ) {
        userRepo.save(new User(firstName, middleName, lastName, birthDate));
        model.addAttribute("message", String.format("Пользователь %s %s успешно добавлен.", middleName, firstName));
        return "result";
    }

    @GetMapping("/remove")
    public String remove(
            @RequestParam Integer id,
            Model model
    ) {
        if (userRepo.existsById(id)) {
            userRepo.deleteById(id);
            model.addAttribute("message", String.format("Пользователь с id: %d успешно удален.", id));
        }
        else {
            model.addAttribute("message", String.format("Пользователь с id: %d не найден.", id));
        }
        return "result";
    }

    @GetMapping("/showall")
    public String showAll(Model model) {
        Iterable<User> users = userRepo.findAll();
        model.addAttribute("Data", users);
        return "users";
    }
}
