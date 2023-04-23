package ru.mirea.task18.services;

import org.springframework.stereotype.Service;
import ru.mirea.task18.entities.Post;
import ru.mirea.task18.entities.User;
import ru.mirea.task18.repos.UserRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void add(String firstName, String middleName, String lastName, String birthDate) {
        userRepository.save(new User(firstName, middleName, lastName, birthDate));
    }

    public void remove(Integer id) {
        userRepository.deleteById(id);
    }

    public List<Post> getAllPosts(Integer id) {
        return userRepository.findPostsByUserId(id);
    }

    public List<User> getAll() {
        return userRepository.findAll();
    }

    public List<User> filter(String criteria, String value) {
        List<User> postList;
        switch (criteria) {
            case "firstName" -> {
                postList = userRepository.findAll().stream()
                        .filter(p -> p.getFirstName().equals(value)).toList();
            }
            case "middleName" -> {
                postList = userRepository.findAll().stream()
                        .filter(p -> p.getMiddleName().equals(value)).toList();
            }
            case "lastName" -> {
                postList = userRepository.findAll().stream()
                        .filter(p -> p.getLastName().equals(value)).toList();
            }
            case "birthDate" -> {
                postList = userRepository.findAll().stream()
                        .filter(u -> u.getBirthDate().equals(value)).toList();
            }
            default -> {
                postList = new ArrayList<>();
            }
        }
        return postList;
    }
}
