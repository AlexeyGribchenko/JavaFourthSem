package ru.mirea.task15.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import ru.mirea.task15.entities.Post;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.mirea.task15.repos.PostRepo;

import java.util.ArrayList;

@Controller
@RequestMapping("/posts")
public class PostController {

    @Autowired
    private PostRepo postRepo;

    @GetMapping("/add")
    public String add(
            @RequestParam(required = false, defaultValue = "example") String text,
            @RequestParam(required = false, defaultValue = "01.01.2000") String creationDate,
            Model model
    ) {
        postRepo.save(new Post(text, creationDate));
        model.addAttribute("message", String.format("Пост %s %s успешно добавлен.", text, creationDate));
        return "result";
    }

    @GetMapping("/remove")
    public String remove(
            @RequestParam Integer id,
            Model model
    ) {
        if (postRepo.existsById(id)) {
            postRepo.deleteById(id);
            model.addAttribute("message", String.format("Пост с id: %d успешно удален.", id));
        }
        else {
            model.addAttribute("message", String.format("Пост с id: %d не найден.", id));
        }
        return "result";
    }

    @GetMapping("/showall")
    public String showAll(Model model) {

        Iterable<Post> posts = postRepo.findAll();
        model.addAttribute("Data", posts);
        return "posts";
    }
}