package ru.mirea.task15.controllers;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class HomeController {

    @GetMapping("/home")
    public String home(Model model) {
        model.addAttribute("name", "Gribchenko Alexey");
        model.addAttribute("group", "ИКБО-10-21");
        model.addAttribute("variant", "Вариант 6");
        return "home";
    }
}
