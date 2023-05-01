package ru.mirea.task23.controllers;


import org.springframework.boot.Banner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class HomeController {

    @RequestMapping("/")
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
}
