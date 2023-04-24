package ru.mirea.task19.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.mirea.task19.entities.Post;
import ru.mirea.task19.entities.User;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    @Query(value = "select u.posts from User where u.id = id", nativeQuery = true)
    List<Post> findPostsByUserId(@Param("id") Integer id);
}
