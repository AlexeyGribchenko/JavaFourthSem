package ru.mirea.task20.services;

import ru.mirea.task20.entities.Post;
import ru.mirea.task20.entities.User;

import java.util.List;

public interface UserService {
    void add(String firstName, String middleName, String lastName, String birthDate);
    void remove(Integer id);
    List<Post> getAllPosts(Integer id);
    List<User> getAll();
    List<User> filter(String criteria, String value);
}
