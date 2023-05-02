package ru.mirea.task24;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.mirea.task24.entities.Post;
import ru.mirea.task24.entities.User;
import ru.mirea.task24.repos.UserRepository;
import ru.mirea.task24.services.implementations.PostServiceImpl;
import ru.mirea.task24.services.implementations.UserServiceImpl;
import ru.mirea.task24.services.interfaces.PostService;
import ru.mirea.task24.services.interfaces.UserService;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.List;

@ExtendWith(MockitoExtension.class)
public class UserServiceImplTest {
    @Mock
    private UserRepository userRepository;

    @Captor
    private ArgumentCaptor<User> captor;

    @Test
    void getUsers() {
        User user1 = new User();
        user1.setFirstName("Артем");
        User user2 = new User();
        user2.setFirstName("Костя");

        Mockito.when(userRepository.findAll()).thenReturn(List.of(user1, user2));
        UserService userService = new UserServiceImpl(userRepository);
        Assertions.assertEquals(2, userService.getAll().size());
        Assertions.assertEquals("Артем", userService.getAll().get(0).getFirstName());
    }

    @Test
    void add() {
        UserService userService = new UserServiceImpl(userRepository);
        userService.add("Витя", "", "", "");
        Mockito.verify(userRepository).save(captor.capture());
        User captured = captor.getValue();
        Assertions.assertEquals("Витя", captured.getFirstName());
    }

    @Test
    void getFilteredByBirthDate() {
        UserService userService = new UserServiceImpl(userRepository);
        User[] massive = new User[] {
                new User("Алексей", "Юрьевич", "Грибченко", "28.06.2003"),
                new User("Виктор", "Дмитриевич", "Герасимов", "19.11.2002"),
                new User("Виктор", "Анатольевич", "Герасимов", "18.05.2003"),
                new User("Константин", "Анатольевич", "Журавлев", "28.06.2003")
        };
        Mockito.when(userRepository.findAll()).thenReturn(List.of(massive));

        Assertions.assertEquals(userService.filter("birthDate", "19.11.2002").size(), 1);
        Assertions.assertEquals(userService.filter("birthDate", "28.06.2003").size(), 2);
    }

    @Test
    void getFilteredByLastname() {
        UserService userService = new UserServiceImpl(userRepository);
        User[] massive = new User[] {
                new User("Алексей", "Юрьевич", "Грибченко", "28.06.2003"),
                new User("Виктор", "Дмитриевич", "Герасимов", "19.11.2002"),
                new User("Виктор", "Анатольевич", "Герасимов", "18.05.2003"),
                new User("Константин", "Анатольевич", "Журавлев", "28.06.2003")
        };
        Mockito.when(userRepository.findAll()).thenReturn(List.of(massive));

        Assertions.assertEquals(userService.filter("lastName", "Грибченко").size(), 1);
        Assertions.assertEquals(userService.filter("lastName", "Герасимов").size(), 2);
    }

    @Test
    void getFilteredByMiddleName() {
        UserService userService = new UserServiceImpl(userRepository);
        User[] massive = new User[] {
                new User("Алексей", "Юрьевич", "Грибченко", "28.06.2003"),
                new User("Виктор", "Дмитриевич", "Герасимов", "19.11.2002"),
                new User("Виктор", "Анатольевич", "Герасимов", "18.05.2003"),
                new User("Константин", "Анатольевич", "Журавлев", "28.06.2003")
        };
        Mockito.when(userRepository.findAll()).thenReturn(List.of(massive));

        Assertions.assertEquals(userService.filter("middleName", "Дмитриевич").size(), 1);
        Assertions.assertEquals(userService.filter("middleName", "Анатольевич").size(), 2);
    }

    @Test
    void getFilteredByFirstName() {
        UserService userService = new UserServiceImpl(userRepository);
        User[] massive = new User[] {
                new User("Алексей", "Юрьевич", "Грибченко", "28.06.2003"),
                new User("Виктор", "Дмитриевич", "Герасимов", "19.11.2002"),
                new User("Виктор", "Анатольевич", "Герасимов", "18.05.2003"),
                new User("Константин", "Анатольевич", "Журавлев", "28.06.2003")
        };
        Mockito.when(userRepository.findAll()).thenReturn(List.of(massive));

        Assertions.assertEquals(userService.filter("firstName", "Алексей").size(), 1);
        Assertions.assertEquals(userService.filter("firstName", "Виктор").size(), 2);
    }
}
