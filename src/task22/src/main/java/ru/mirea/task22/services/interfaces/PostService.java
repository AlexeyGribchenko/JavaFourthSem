package ru.mirea.task22.services.interfaces;

import ru.mirea.task22.entities.Post;
import ru.mirea.task22.entities.User;

import java.util.List;

public interface PostService {
    void add(String text, String creationDate, Integer userId);
    void remove(Integer id);
    Post getPost(Integer id) throws IllegalStateException;
    User getUserByPost(Integer id) throws IllegalStateException;
    List<Post> getAll();
    List<Post> filter(String criteria, String value);
}
