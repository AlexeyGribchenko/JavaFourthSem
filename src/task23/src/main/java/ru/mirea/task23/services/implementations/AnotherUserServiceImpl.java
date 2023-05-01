package ru.mirea.task23.services.implementations;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.mirea.task23.entities.AnotherUser;
import ru.mirea.task23.entities.enums.Role;
import ru.mirea.task23.repos.AnotherUserRepo;
import ru.mirea.task23.services.interfaces.AnotherUserService;

@Service
public class AnotherUserServiceImpl implements AnotherUserService {

    private final AnotherUserRepo anotherUserRepo;

    private final PasswordEncoder encoder;

    public AnotherUserServiceImpl(AnotherUserRepo anotherUserRepo, PasswordEncoder encoder) {
        this.anotherUserRepo = anotherUserRepo;
        this.encoder = encoder;
    }

    @Override
    public boolean add(AnotherUser anotherUser) {
        if (anotherUserRepo.findByUsername(anotherUser.getUsername()) != null) {
            return false;
        }
        anotherUser.setRoles(Role.ROLE_USER);
        anotherUser.setPassword(encoder.encode(anotherUser.getPassword()));
        anotherUser.setActive(true);
        anotherUserRepo.save(anotherUser);
        return true;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        AnotherUser anotherUser = anotherUserRepo.findByUsername(username);

        if (anotherUser == null) {
            throw new UsernameNotFoundException("Пользователь не найден!");
        }

        return anotherUser;
    }
}
