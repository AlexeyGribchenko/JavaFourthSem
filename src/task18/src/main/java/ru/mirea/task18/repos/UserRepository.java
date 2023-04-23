package ru.mirea.task18.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.RepositoryDefinition;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.mirea.task18.entities.Post;
import ru.mirea.task18.entities.User;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    @Query(value = "select p from Post p where p.user.id = id", nativeQuery = true)
    List<Post> findPostsByUserId(@Param("id") Integer id);
}
