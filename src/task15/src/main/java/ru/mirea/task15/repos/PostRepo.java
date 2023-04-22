package ru.mirea.task15.repos;

import org.springframework.data.repository.CrudRepository;
import ru.mirea.task15.entities.Post;

public interface PostRepo extends CrudRepository<Post, Integer> {
}
