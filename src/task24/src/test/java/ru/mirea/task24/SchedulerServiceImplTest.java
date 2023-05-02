package ru.mirea.task24;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.mirea.task24.services.interfaces.SchedulerService;

import java.io.IOException;

@ExtendWith(MockitoExtension.class)
public class SchedulerServiceImplTest {

    @Mock
    private SchedulerService schedulerService;

    @Test
    public void runTask() {
        try {
            schedulerService.doScheduledTask();
        } catch (IOException e) {
            System.out.println("Error! Something went wrong!");
            throw new RuntimeException(e);
        }
    }
}
