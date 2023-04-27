package ru.mirea.task22.services.implementations;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import ru.mirea.task22.entities.Post;
import ru.mirea.task22.entities.User;
import ru.mirea.task22.services.interfaces.PostService;
import ru.mirea.task22.services.interfaces.SchedulerService;
import ru.mirea.task22.services.interfaces.UserService;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

@Service
public class SchedulerServiceImpl implements SchedulerService {

    private final PostService postService;
    private final UserService userService;

    @Value("${directories.post}")
    private String postSrcString;
    @Value("${directories.user}")
    private String userSrcString;

    private Path postPath;
    private Path userPath;

    public SchedulerServiceImpl(PostService postService, UserService userService) {
        this.postService = postService;
        this.userService = userService;
    }

    @Override
    @Scheduled(fixedDelay = 1800000)
    public void doScheduledTask() throws IOException {
        Path postPath = Path.of(postSrcString);
        Path userPath = Path.of(userSrcString);

        if (Files.exists(postPath)) {
            Files.delete(postPath);
        }
        if (Files.exists(userPath)) {
            Files.delete(userPath);
        }

        List<Post> postList = postService.getAll();
        List<User> userList = userService.getAll();

        FileWriter postFileWriter = new FileWriter(postSrcString);
        for (Post p : postList) {
            postFileWriter.write(p.toString() + "\n");
        }
        postFileWriter.close();

        FileWriter userFileWriter = new FileWriter(userSrcString);
        for (User u : userList) {
            userFileWriter.write(u.toString() + "\nl");
        }
        userFileWriter.close();
    }
}
