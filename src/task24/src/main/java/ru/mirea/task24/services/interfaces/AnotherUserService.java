package ru.mirea.task24.services.interfaces;

import org.springframework.security.core.userdetails.UserDetailsService;
import ru.mirea.task24.entities.AnotherUser;

public interface AnotherUserService extends UserDetailsService {

    boolean add(AnotherUser anotherUser);
}
