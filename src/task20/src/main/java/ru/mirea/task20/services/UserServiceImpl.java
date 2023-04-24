package ru.mirea.task20.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.mirea.task20.entities.Post;
import ru.mirea.task20.entities.User;
import ru.mirea.task20.repos.UserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void add(String firstName, String middleName, String lastName, String birthDate) {
        log.info("New user added: " + firstName + " "  + lastName);
        userRepository.save(new User(firstName, middleName, lastName, birthDate));
    }

    public void remove(Integer id) {
        if (userRepository.findById(id).isPresent()) {
            userRepository.deleteById(id);
            log.info("User (" + id.toString() + ") deleted");
        } else {
            log.warn("User (" + id.toString() + ") not found!");
        }
    }

    public List<Post> getAllPosts(Integer id) {
        Optional<User> userOptional = userRepository.findById(id);
        if (userOptional.isPresent()) {
            log.info("(User) Method getAllPosts called");
            return userOptional.get().getPosts();
        } else {
            log.warn("User (" + id.toString() + ") not found!");
            return new ArrayList<>();
        }
    }

    public List<User> getAll() {
        log.info("(User) Method getAll called");
        return userRepository.findAll();
    }

    public List<User> filter(String criteria, String value) throws IllegalArgumentException {
        List<User> userList;
        switch (criteria) {
            case "firstName" -> {
                userList = userRepository.findAll().stream()
                        .filter(p -> p.getFirstName().equals(value)).toList();
            }
            case "middleName" -> {
                userList = userRepository.findAll().stream()
                        .filter(p -> p.getMiddleName().equals(value)).toList();
            }
            case "lastName" -> {
                userList = userRepository.findAll().stream()
                        .filter(p -> p.getLastName().equals(value)).toList();
            }
            case "birthDate" -> {
                userList = userRepository.findAll().stream()
                        .filter(u -> u.getBirthDate().equals(value)).toList();
            }
            default -> {
                log.warn("(User) Attribute does not exist: " + criteria);
                throw new IllegalArgumentException("Attribute does not exist: " + criteria);
            }
        }
        log.info("(User) Method filter {" + criteria + "} called");
        return userList;
    }
}
