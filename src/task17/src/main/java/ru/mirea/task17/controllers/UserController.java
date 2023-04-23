package ru.mirea.task17.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.mirea.task17.entities.Post;
import ru.mirea.task17.entities.User;
import ru.mirea.task17.sessions.UserService;

import java.util.List;


@Controller
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/add")
    public String add(
            @RequestParam(required = false, defaultValue = "Иван") String firstName,
            @RequestParam(required = false, defaultValue = "Иванов") String middleName,
            @RequestParam(required = false, defaultValue = "Иванович") String lastName,
            @RequestParam(required = false, defaultValue = "01.01.2000") String birthDate,
            Model model
    ) {
        userService.add(firstName, middleName, lastName, birthDate);
        model.addAttribute("message", String.format("Пользователь %s %s успешно добавлен.", middleName, firstName));
        return "result";
    }

    @GetMapping("/remove")
    public String remove(
            @RequestParam Integer id,
            Model model
    ) {
        userService.remove(id);
        model.addAttribute("message", String.format("Пользователь с id: %d успешно удален.", id));
        return "result";
    }

    @GetMapping("/{id}/posts")
    @ResponseBody
    public List<Post> showAllUserPosts(@PathVariable("id") Integer id, Model model) {
        return userService.getAllPosts(id);
    }

    @GetMapping("/all")
    public String showAll(Model model) {
        Iterable<User> users = userService.getAll();
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
        List<User> users = userService.filter(criteria, value);
        model.addAttribute("Data", users);
        return "users";
    }
}
