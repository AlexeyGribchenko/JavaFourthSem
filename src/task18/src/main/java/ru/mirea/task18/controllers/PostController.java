package ru.mirea.task18.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.mirea.task18.entities.Post;
import ru.mirea.task18.entities.User;
import ru.mirea.task18.services.PostServiceImpl;


import java.util.List;


@Controller
@RequestMapping("/posts")
public class PostController {

    private final PostServiceImpl postServiceImpl;

    public PostController(PostServiceImpl postServiceImpl) {
        this.postServiceImpl = postServiceImpl;
    }

    @GetMapping("/add")
    public String add(
            @RequestParam(required = false, defaultValue = "example") String text,
            @RequestParam(required = false, defaultValue = "01.01.2000") String creationDate,
            @RequestParam(required = false, defaultValue = "1") Integer user_id,
            Model model
    ) {
        postServiceImpl.add(text, creationDate, user_id);
        model.addAttribute("message", String.format("Пост %s %s успешно добавлен.", text, creationDate));
        return "result";
    }

    @GetMapping("/remove")
    public String remove(
            @RequestParam Integer id,
            Model model
    ) {
        postServiceImpl.remove(id);
        model.addAttribute("message", String.format("Пост с id: %d успешно удален.", id));
        return "result";
    }

    @GetMapping("/all")
    public String showAll(Model model) {
        Iterable<Post> posts = postServiceImpl.getAll();
        model.addAttribute("Data", posts);
        return "posts";
    }

    @GetMapping("/{postId}")
    public @ResponseBody Post getPost(@PathVariable(value = "postId") Integer id) {
        try {
            return postServiceImpl.getPost(id);
        } catch (IllegalStateException e) {
            return null;
        }
    }

    @GetMapping("/{postId}/user")
    @ResponseBody
    public User getUserByPost(@PathVariable("postId") Integer id) {
        try {
            return postServiceImpl.getUserByPost(id);
        } catch (IllegalStateException e) {
            return null;
        }
    }

    @GetMapping("/filter/{criteria}/{value}")
    public String filter(
            @PathVariable("criteria") String criteria,
            @PathVariable("value") String value,
            Model model
    ) {
        if (!criteria.equals("text") && !criteria.equals("creation_date")) {
            model.addAttribute("message", String.format("No such attribute: %s", criteria));
            return "result";
        }
        List<Post> posts = postServiceImpl.filter(criteria, value);
        model.addAttribute("Data", posts);
        return "posts";
    }
}