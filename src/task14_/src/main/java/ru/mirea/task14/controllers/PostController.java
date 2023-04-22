package ru.mirea.task14.controllers;

import ru.mirea.task14.entities.Post;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;

@Controller
@RequestMapping("/post")
public class PostController {

    private ArrayList<Post> postList;

    @GetMapping("/add")
    public String add(
            @RequestParam(required = false, defaultValue = "example") String text,
            @RequestParam(required = false, defaultValue = "01.01.2000") String creationDate,
            Model model
    ) {
        postList.add(new Post(text, creationDate));
        model.addAttribute("message", String.format("Пост %s %s успешно добавлен.", text, creationDate));
        return "result";
    }

    @GetMapping("/remove")
    public String remove(
            @RequestParam String text,
            @RequestParam String creationDate,
            Model model
    ) {
        if (postList.removeIf((post -> post.equals(new Post(text, creationDate))))) {
            model.addAttribute("message", String.format("Пост от %s успешно удален.", creationDate));
        } else {
            model.addAttribute("message", "Пост не найден!");
        }
        return "result";
    }

    @GetMapping("/showall")
    public String showAll(Model model) {
        model.addAttribute("Type", "Посты");
        model.addAttribute("Data", postList);
        return "posts";
    }
}