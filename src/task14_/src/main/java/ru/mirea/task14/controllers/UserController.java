package ru.mirea.task14.controllers;

import ru.mirea.task14.entities.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;

@Controller
@RequestMapping("/users")
public class UserController {

    private final ArrayList<User> userList = new ArrayList<>();

    @GetMapping("/add")
    public String add(
            @RequestParam(required = false, defaultValue = "Иван") String firstName,
            @RequestParam(required = false, defaultValue = "Иванов") String middleName,
            @RequestParam(required = false, defaultValue = "Иванович") String lastName,
            @RequestParam(required = false, defaultValue = "01.01.2000") String birthDate,
            Model model
    ) {
        userList.add(new User(firstName, middleName, lastName, birthDate));
        model.addAttribute("message", String.format("Пользователь %s %s успешно добавлен.", middleName, firstName));
        return "result";
    }

    @GetMapping("/remove")
    public String remove(
            @RequestParam String firstName,
            @RequestParam String lastName,
            @RequestParam String middleName,
            @RequestParam String birthDate,
            Model model
    ) {
        if (userList.removeIf((user -> user.equals(new User(firstName, middleName, lastName, birthDate))))) {
            model.addAttribute("message", String.format("Пользователь %s %s успешно удален.", middleName, firstName));
        } else {
            model.addAttribute("message", "Пользователь не найден!");
        }
        return "result";
    }

    @GetMapping("/showall")
    public String showAll(Model model) {
        model.addAttribute("Type", "Пользователи");
        model.addAttribute("Data", userList);
        return "users";
    }
}
