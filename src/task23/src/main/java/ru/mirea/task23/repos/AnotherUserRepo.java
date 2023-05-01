package ru.mirea.task23.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.mirea.task23.entities.AnotherUser;

@Repository
public interface AnotherUserRepo extends JpaRepository<AnotherUser, Integer> {
    AnotherUser findByUsername(String username);
}
