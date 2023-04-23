package ru.mirea.task16.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.mirea.task16.entities.Post;
import ru.mirea.task16.entities.User;
import ru.mirea.task16.sessions.PostService;


@Controller
@RequestMapping("/posts")
public class PostController {

    @Autowired
    private PostService postService;

    @GetMapping("/add")
    public String add(
            @RequestParam(required = false, defaultValue = "example") String text,
            @RequestParam(required = false, defaultValue = "01.01.2000") String creationDate,
            @RequestParam(required = false, defaultValue = "1") Integer user_id,
            Model model
    ) {
        postService.add(text, creationDate, user_id);
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

    @GetMapping("/{postId}")
    public @ResponseBody Post getPost(@PathVariable(value = "postId") Integer id) {
        return postService.getPost(id);
    }

    @GetMapping("/{postId}/user")
    @ResponseBody
    public User getUserByPost(@PathVariable("postId") Integer id) {
        return postService.getUserByPost(id);
    }
}