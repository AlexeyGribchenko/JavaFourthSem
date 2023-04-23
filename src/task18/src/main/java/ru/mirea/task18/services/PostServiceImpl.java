package ru.mirea.task18.services;

import org.springframework.stereotype.Service;
import ru.mirea.task18.entities.Post;
import ru.mirea.task18.entities.User;
import ru.mirea.task18.repos.PostRepository;
import ru.mirea.task18.repos.UserRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;

    private final UserRepository userRepository;

    public PostServiceImpl(PostRepository postRepository, UserRepository userRepository) {
        this.postRepository = postRepository;
        this.userRepository = userRepository;
    }

    public void add(String text, String creationDate, Integer userId) {
        if (userRepository.findById(userId).isPresent()) {
            postRepository.save(new Post(text, creationDate, userRepository.findById(userId).get()));
        }
    }

    public void remove(Integer id) {
        postRepository.deleteById(id);
    }

    public Post getPost(Integer id) throws IllegalStateException {
        return postRepository.findById(id).orElseThrow(() ->
                new IllegalStateException("Post with such id doesn't exist!")
        );
    }

    public User getUserByPost(Integer id) throws IllegalStateException {
        return postRepository.findById(id).orElseThrow(() ->
                new IllegalStateException("Post with such id doesn't exist!")
        ).getUser();
    }

    public List<Post> getAll() {
        return postRepository.findAll();
    }

    public List<Post> filter(String criteria, String value) {
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
                postList = new ArrayList<>();
            }
        }
        return postList;
    }
}
