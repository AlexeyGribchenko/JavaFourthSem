package ru.mirea.task15.repos;

import org.springframework.data.repository.CrudRepository;
import ru.mirea.task15.entities.User;

public interface UserRepo extends CrudRepository<User, Integer> {
}
