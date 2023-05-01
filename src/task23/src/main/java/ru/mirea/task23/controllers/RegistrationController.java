package ru.mirea.task23.controllers;

import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import ru.mirea.task23.entities.AnotherUser;
import ru.mirea.task23.services.interfaces.AnotherUserService;


@Controller
public class RegistrationController {

    private final AnotherUserService anotherUserService;

    public RegistrationController(AnotherUserService anotherUserService) {
        this.anotherUserService = anotherUserService;
    }


    @GetMapping("/registration")
    public String registration(Model model) {
        model.addAttribute("userForm", new AnotherUser());
        return "registration";
    }

    @PostMapping("/registration")
    public String addUser(
            @ModelAttribute("userForm") AnotherUser anotherUser, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "registration";
        }
        if (!anotherUserService.add(anotherUser)) {
            model.addAttribute("is_exist", true);
            model.addAttribute("message", "Пользователь с заданным именем уже существует!");
            return "registration";
        }
        model.addAttribute("is_exist", true);
        model.addAttribute("message", "Успешная регистрация!");
        return "registration";
    }
}
