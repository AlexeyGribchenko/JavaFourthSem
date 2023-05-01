package ru.mirea.task23.services.interfaces;

import org.springframework.security.core.userdetails.UserDetailsService;
import ru.mirea.task23.entities.AnotherUser;

public interface AnotherUserService extends UserDetailsService {

    boolean add(AnotherUser anotherUser);
}
