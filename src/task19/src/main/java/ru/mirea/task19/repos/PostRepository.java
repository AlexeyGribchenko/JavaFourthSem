package ru.mirea.task19.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.mirea.task19.entities.Post;

@Repository
public interface PostRepository extends JpaRepository<Post, Integer> {
}
