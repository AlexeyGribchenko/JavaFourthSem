package ru.mirea.task24.controllers;


import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.mirea.task24.entities.AnotherUser;


@Controller
public class HomeController {

    @GetMapping("/")
    public String start() {
        return "index";
    }

    @GetMapping("/home")
    public String home(Model model) {
        model.addAttribute("name", "Gribchenko Alexey");
        model.addAttribute("group", "ИКБО-10-21");
        model.addAttribute("variant", "Вариант 6");
        return "home";
    }

    @GetMapping("/info")
    public String info(@AuthenticationPrincipal AnotherUser anotherUser, Model model) {
        model.addAttribute("message", anotherUser.getUsername());
        return "result";
    }
}
