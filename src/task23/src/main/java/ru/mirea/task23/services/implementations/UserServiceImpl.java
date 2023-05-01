package ru.mirea.task23.services.implementations;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.mirea.task23.entities.Post;
import ru.mirea.task23.entities.User;
import ru.mirea.task23.repos.UserRepository;
import ru.mirea.task23.services.interfaces.UserService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final EmailService emailService;

    public UserServiceImpl(UserRepository userRepository, EmailService emailService) {
        this.userRepository = userRepository;
        this.emailService = emailService;
    }

    @Transactional
    public void add(String firstName, String middleName, String lastName, String birthDate) {
        log.info("New user added: " + firstName + " "  + lastName);
        userRepository.save(new User(firstName, middleName, lastName, birthDate));
        emailService.send("alesha.gribchenko@yandex.ru", "Creating a user",
                String.format("User: \n\tFirst name: %s \n\tLast name: %s \n\tMiddle name: %s \n\tBirth date: %s",
                        firstName, lastName, middleName, birthDate));
    }

    @Transactional
    public void remove(Integer id) throws IllegalArgumentException {
        if (userRepository.findById(id).isPresent()) {
            userRepository.deleteById(id);
            log.info("User (" + id + ") deleted");
        } else {
            log.warn("User (" + id + ") not found!");
            throw new IllegalArgumentException("User (" + id + ") not found!");
        }
    }

    @Transactional(readOnly = true)
    public List<Post> getAllPosts(Integer id) {
        Optional<User> userOptional = userRepository.findById(id);
        if (userOptional.isPresent()) {
            log.info("(User) Method getAllPosts called");
            return userOptional.get().getPosts();
        } else {
            log.warn("User (" + id + ") not found!");
            return new ArrayList<>();
        }
    }

    @Transactional(readOnly = true)
    public List<User> getAll() {
        log.info("(User) Method getAll called");
        return userRepository.findAll();
    }

    @Transactional
    public List<User> filter(String criteria, String value) throws IllegalArgumentException {
        List<User> userList;
        switch (criteria) {

            case "firstName" -> userList = userRepository.findAll()
                    .stream().filter(p -> p.getFirstName().equals(value)).toList();

            case "middleName" -> userList = userRepository.findAll()
                    .stream().filter(p -> p.getMiddleName().equals(value)).toList();

            case "lastName" -> userList = userRepository.findAll()
                    .stream().filter(p -> p.getLastName().equals(value)).toList();

            case "birthDate" -> userList = userRepository.findAll()
                    .stream().filter(u -> u.getBirthDate().equals(value)).toList();

            default -> {
                log.warn("(User) Attribute does not exist: " + criteria);
                throw new IllegalArgumentException("Attribute does not exist: " + criteria);
            }
        }
        log.info("(User) Method filter {" + criteria + "} called");
        return userList;
    }
}
