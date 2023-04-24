package ru.mirea.task19.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.mirea.task19.entities.Post;
import ru.mirea.task19.entities.User;
import ru.mirea.task19.repos.PostRepository;
import ru.mirea.task19.repos.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;

    private final UserRepository userRepository;

    public PostServiceImpl(PostRepository postRepository, UserRepository userRepository) {
        this.postRepository = postRepository;
        this.userRepository = userRepository;
    }

    public void add(String text, String creationDate, Integer userId) throws IllegalArgumentException {
        if (userRepository.findById(userId).isPresent()) {
            postRepository.save(new Post(text, creationDate, userRepository.findById(userId).get()));
            log.info("(Post) New post added: " + text + " " + creationDate);
        } else {
            log.warn("(Post) Failed to add new Post: User (" + userId + ") not found!");
            throw new IllegalArgumentException("Failed to add new Post: User (" + userId + ") not found!");
        }
    }

    public void remove(Integer id) throws IllegalArgumentException {
        if (postRepository.findById(id).isPresent()) {
            postRepository.deleteById(id);
            log.info("(Post) Post (" + id.toString() + ") successfully deleted");
        } else {
            log.warn("(Post) Post (" + id.toString() + ") not found!");
            throw new IllegalArgumentException("Post (" + id.toString() + ") not found!");
        }
    }

    public Post getPost(Integer id) throws IllegalStateException {
        Optional<Post> optionalPost = postRepository.findById(id);
        if (optionalPost.isPresent()) {
            log.info("(Post) Method getPost called");
            return optionalPost.get();
        } else {
            log.warn("(Post) Post (" + id.toString() + ") not found!");
            throw new IllegalStateException("Post with such id doesn't exist!");
        }
    }

    public User getUserByPost(Integer id) throws IllegalStateException {
        Optional<Post> optionalPost = postRepository.findById(id);
        if (optionalPost.isPresent()) {
            log.info("(Post) Method getUserByPost called");
            return optionalPost.get().getUser();
        } else {
            log.warn("(Post) Post (" + id.toString() + ") not found!");
            throw new IllegalStateException("Post with such id doesn't exist!");
        }
    }

    public List<Post> getAll() {
        log.info("(Post) Method getAll called");
        return postRepository.findAll();
    }

    public List<Post> filter(String criteria, String value) throws IllegalArgumentException {
        List<Post> postList;
        switch (criteria) {
            case "text" -> {
                postList = postRepository.findAll().stream()
                        .filter(p -> p.getText().equals(value)).toList();
            }
            case "creationDate" -> {
                postList = postRepository.findAll().stream()
                        .filter(p -> p.getCreationDate().equals(value)).toList();
            }
            default -> {
                log.warn("(Post) Attribute does not exist: " + criteria);
                throw new IllegalStateException("Attribute does not exist: " + criteria);
            }
        }
        log.info("(Post) Method filter {" + criteria + "} called");
        return postList;
    }
}
