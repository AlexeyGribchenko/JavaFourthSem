package ru.mirea.task24;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.mirea.task24.entities.Post;
import ru.mirea.task24.entities.User;
import ru.mirea.task24.repos.PostRepository;
import ru.mirea.task24.repos.UserRepository;
import ru.mirea.task24.services.implementations.PostServiceImpl;
import ru.mirea.task24.services.interfaces.PostService;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class PostServiceImplTest {

    @Mock
    private PostRepository postRepository;
    @Mock
    private UserRepository userRepository;

    @Captor
    private ArgumentCaptor<Post> captor;

    @Test
    public void getAll() {
        User user = new User();
        Post post1 = new Post();
        post1.setUser(user);
        Post post2 = new Post();
        post2.setUser(user);
        PostService postService = new PostServiceImpl(postRepository, userRepository);
        Mockito.when(postRepository.findAll()).thenReturn(List.of(post1, post2));
        Assertions.assertEquals(2, postService.getAll().size());
        Assertions.assertEquals(user, postService.getAll().get(0).getUser());
    }

    @Test
    void add() {
        User user = new User();
        user.setId(1);
        Mockito.when(userRepository.findById(1)).thenReturn(Optional.of(user));
        PostService postService = new PostServiceImpl(postRepository, userRepository);
        postService.add("текст", "", 1);
        Mockito.verify(postRepository).save(captor.capture());
        Post captured = captor.getValue();
        Assertions.assertEquals("текст", captured.getText());
    }

    @Test
    void getFilteredByCreationDate() {
        PostService postService = new PostServiceImpl(postRepository, userRepository);
        User user = new User();
        userRepository.save(user);
        Post[] massive = new Post[] {
                new Post("текст1", "10.03.2003", user),
                new Post("текст2", "20.03.2023", user),
                new Post("текст3", "20.04.2023", user),
                new Post("текст3", "20.04.2023", user)
        };
        Mockito.when(postRepository.findAll()).thenReturn(List.of(massive));

        Assertions.assertEquals(postService.filter("creationDate", "20.04.2023").size(), 2);
        Assertions.assertEquals(postService.filter("creationDate", "10.03.2003").size(), 1);
    }

    @Test
    void getFilteredByText() {
        PostService postService = new PostServiceImpl(postRepository, userRepository);
        User user = new User();
        userRepository.save(user);
        Post[] massive = new Post[] {
                new Post("текст1", "10.03.2003", user),
                new Post("текст2", "20.03.2023", user),
                new Post("текст3", "20.04.2023", user),
                new Post("текст3", "20.04.2023", user)
        };
        Mockito.when(postRepository.findAll()).thenReturn(List.of(massive));

        Assertions.assertEquals(postService.filter("text", "текст3").size(), 2);
        Assertions.assertEquals(postService.filter("text", "текст1").size(), 1);
    }
}
