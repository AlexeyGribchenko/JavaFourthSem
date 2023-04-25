package ru.mirea.task21.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.mirea.task21.entities.Post;

@Repository
public interface PostRepository extends JpaRepository<Post, Integer> {
}
