package ru.mirea.task23.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.mirea.task23.entities.Post;
import ru.mirea.task23.entities.User;
import ru.mirea.task23.services.interfaces.PostService;

import java.util.List;


@Controller
@RequestMapping("/posts")
public class PostController {

    private final PostService postServiceImpl;

    public PostController(PostService postServiceImpl) {
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
        try {
            postServiceImpl.remove(id);
            model.addAttribute("message", String.format("Пост с id: %d успешно удален.", id));
        } catch (IllegalArgumentException e) {
            model.addAttribute("message", e.getMessage());
        }
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
        } catch (IllegalArgumentException e) {
            return null;
        }
    }

    @GetMapping("/{postId}/user")
    @ResponseBody
    public User getUserByPost(@PathVariable("postId") Integer id) {
        try {
            return postServiceImpl.getUserByPost(id);
        } catch (IllegalArgumentException e) {
            return null;
        }
    }

    @GetMapping("/filter/{criteria}/{value}")
    public String filter(
            @PathVariable("criteria") String criteria,
            @PathVariable("value") String value,
            Model model
    ) {
        try {
            List<Post> posts = postServiceImpl.filter(criteria, value);
            model.addAttribute("Data", posts);
            return "posts";
        } catch (IllegalArgumentException e) {
            model.addAttribute("message", e.getMessage());
            return "result";
        }
    }
}