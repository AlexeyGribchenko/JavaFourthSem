package ru.mirea.task24;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.mirea.task24.services.implementations.EmailService;

@ExtendWith(MockitoExtension.class)
public class EmailServiceImplTest {

    @Mock
    private EmailService emailService;

    @Test
    public void sendMail() {
        emailService.send("alesha.gribchenko@yandex.ru", "Email test", "Success!");
    }
}
