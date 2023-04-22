package ru.mirea.task15_2.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import ru.mirea.task15_2.entities.Post;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.mirea.task15_2.sessions.PostService;

@Controller
@RequestMapping("/posts")
public class PostController {

    @Autowired
    private PostService postService;

    @GetMapping("/add")
    public String add(
            @RequestParam(required = false, defaultValue = "example") String text,
            @RequestParam(required = false, defaultValue = "01.01.2000") String creationDate,
            Model model
    ) {
        postService.add(text, creationDate);
        model.addAttribute("message", String.format("Пост %s %s успешно добавлен.", text, creationDate));
        return "result";
    }

    @GetMapping("/remove")
    public String remove(
            @RequestParam Integer id,
            Model model
    ) {
        postService.remove(id);
        model.addAttribute("message", String.format("Пост с id: %d успешно удален.", id));
        return "result";
    }

    @GetMapping("/showall")
    public String showAll(Model model) {
        Iterable<Post> posts = postService.getAll();;
        model.addAttribute("Data", posts);
        return "posts";
    }
}