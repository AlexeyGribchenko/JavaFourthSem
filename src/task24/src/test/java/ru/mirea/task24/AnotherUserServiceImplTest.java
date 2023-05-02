package ru.mirea.task24;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;
import ru.mirea.task24.entities.AnotherUser;
import ru.mirea.task24.repos.AnotherUserRepo;
import ru.mirea.task24.services.implementations.AnotherUserServiceImpl;
import ru.mirea.task24.services.interfaces.AnotherUserService;

@ExtendWith(MockitoExtension.class)
public class AnotherUserServiceImplTest {

    @Mock
    private AnotherUserRepo anotherUserRepo;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Captor
    private ArgumentCaptor<AnotherUser> captor;

    @Test
    void addUser() {

        AnotherUserService anotherUserService = new AnotherUserServiceImpl(anotherUserRepo, passwordEncoder);
        AnotherUser user = new AnotherUser();
        user.setUsername("login");
        user.setPassword(passwordEncoder.encode("password"));
        anotherUserService.add(user);
        Mockito.verify(anotherUserRepo).save(captor.capture());
        AnotherUser captured = captor.getValue();
        Assertions.assertEquals("login", captured.getUsername());
        Assertions.assertEquals(passwordEncoder.encode("password"),
                captured.getPassword());
    }

}
