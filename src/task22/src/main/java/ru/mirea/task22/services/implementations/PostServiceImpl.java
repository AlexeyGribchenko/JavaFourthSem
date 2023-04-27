package ru.mirea.task22.services.implementations;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.mirea.task22.entities.Post;
import ru.mirea.task22.entities.User;
import ru.mirea.task22.repos.PostRepository;
import ru.mirea.task22.repos.UserRepository;
import ru.mirea.task22.services.interfaces.PostService;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final EmailService emailService;

    public PostServiceImpl(PostRepository postRepository, UserRepository userRepository, EmailService emailService) {
        this.postRepository = postRepository;
        this.userRepository = userRepository;
        this.emailService = emailService;
    }

    @Transactional
    public void add(String text, String creationDate, Integer userId) throws IllegalArgumentException {
        if (userRepository.findById(userId).isPresent()) {
            postRepository.save(new Post(text, creationDate, userRepository.findById(userId).get()));
            log.info("(Post) New post added: " + text + " " + creationDate);
            emailService.send("alesha.gribchenko@yandex.ru", "Creating a post",
                    String.format("Post: \n\tText: %s \n\tCreation date: %s \n\tUser id: %d",
                            text, creationDate, userId));
        } else {
            log.warn("(Post) Failed to add new Post: User (" + userId + ") not found!");
            throw new IllegalArgumentException("Failed to add new Post: User (" + userId + ") not found!");
        }
    }

    @Transactional
    public void remove(Integer id) throws IllegalArgumentException {
        if (postRepository.findById(id).isPresent()) {
            postRepository.deleteById(id);
            log.info("(Post) Post (" + id + ") successfully deleted");
        } else {
            log.warn("(Post) Post (" + id + ") not found!");
            throw new IllegalArgumentException("Post (" + id + ") not found!");
        }
    }

    @Transactional(readOnly = true)
    public Post getPost(Integer id) throws IllegalArgumentException {
        Optional<Post> optionalPost = postRepository.findById(id);
        if (optionalPost.isPresent()) {
            log.info("(Post) Method getPost called");
            return optionalPost.get();
        } else {
            log.warn("(Post) Post (" + id + ") not found!");
            throw new IllegalArgumentException("Post with such id doesn't exist!");
        }
    }

    @Transactional(readOnly = true)
    public User getUserByPost(Integer id) throws IllegalArgumentException {
        Optional<Post> optionalPost = postRepository.findById(id);
        if (optionalPost.isPresent()) {
            log.info("(Post) Method getUserByPost called");
            return optionalPost.get().getUser();
        } else {
            log.warn("(Post) Post (" + id + ") not found!");
            throw new IllegalArgumentException("Post with such id doesn't exist!");
        }
    }

    @Transactional(readOnly = true)
    public List<Post> getAll() {
        log.info("(Post) Method getAll called");
        return postRepository.findAll();
    }

    @Transactional
    public List<Post> filter(String criteria, String value) throws IllegalArgumentException {
        List<Post> postList;
        switch (criteria) {

            case "text" -> postList = postRepository.findAll()
                    .stream().filter(p -> p.getText().equals(value)).toList();

            case "creationDate" -> postList = postRepository.findAll()
                    .stream().filter(p -> p.getCreationDate().equals(value)).toList();

            default -> {
                log.warn("(Post) Attribute does not exist: " + criteria);
                throw new IllegalArgumentException("Attribute does not exist: " + criteria);
            }
        }
        log.info("(Post) Method filter {" + criteria + "} called");
        return postList;
    }
}
