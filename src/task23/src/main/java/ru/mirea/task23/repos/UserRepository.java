package ru.mirea.task23.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.mirea.task23.entities.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

}
