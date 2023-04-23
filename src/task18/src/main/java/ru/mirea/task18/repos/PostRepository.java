package ru.mirea.task18.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.mirea.task18.entities.Post;

@Repository
public interface PostRepository extends JpaRepository<Post, Integer> {
}
