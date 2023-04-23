package ru.mirea.task18.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.mirea.task18.entities.Post;
import ru.mirea.task18.entities.User;
import ru.mirea.task18.services.UserServiceImpl;

import java.util.List;


@Controller
@RequestMapping("/users")
public class UserController {

    private final UserServiceImpl userServiceImpl;

    public UserController(UserServiceImpl userServiceImpl) {
        this.userServiceImpl = userServiceImpl;
    }

    @GetMapping("/add")
    public String add(
            @RequestParam(required = false, defaultValue = "Иван") String firstName,
            @RequestParam(required = false, defaultValue = "Иванов") String middleName,
            @RequestParam(required = false, defaultValue = "Иванович") String lastName,
            @RequestParam(required = false, defaultValue = "01.01.2000") String birthDate,
            Model model
    ) {

        userServiceImpl.add(firstName, middleName, lastName, birthDate);
        model.addAttribute("message", String.format("Пользователь %s %s успешно добавлен.", middleName, firstName));
        return "result";
    }

    @GetMapping("/remove")
    public String remove(
            @RequestParam Integer id,
            Model model
    ) {
        userServiceImpl.remove(id);
        model.addAttribute("message", String.format("Пользователь с id: %d успешно удален.", id));
        return "result";
    }

    @GetMapping("/{id}/posts")
    @ResponseBody
    public List<Post> showAllUserPosts(@PathVariable("id") Integer id) {
        return userServiceImpl.getAllPosts(id);
    }

    @GetMapping("/all")
    public String showAll(Model model) {
        Iterable<User> users = userServiceImpl.getAll();
        model.addAttribute("Data", users);
        return "users";
    }

    @GetMapping("/filter/{criteria}/{value}")
    public String filter(
            @PathVariable("criteria") String criteria,
            @PathVariable("value") String value,
            Model model
    ) {
        if (!criteria.equals("firstName") && !criteria.equals("middleName") &&
                !criteria.equals("lastName") && !criteria.equals("birthDate")) {
            model.addAttribute("message", String.format("No such attribute: %s", criteria));
            return "result";
        }
        List<User> users = userServiceImpl.filter(criteria, value);
        model.addAttribute("Data", users);
        return "users";
    }
}
