package ru.mirea.task22.jmx;

import org.springframework.jmx.export.annotation.ManagedOperation;
import org.springframework.jmx.export.annotation.ManagedResource;
import org.springframework.stereotype.Component;
import ru.mirea.task22.services.interfaces.SchedulerService;

import java.io.IOException;

@Component
@ManagedResource(description = "Reload data from database to file")
public class SchedulerMBean {

    public final SchedulerService schedulerService;

    public SchedulerMBean(SchedulerService schedulerService) {
        this.schedulerService = schedulerService;
    }

    @ManagedOperation(description = "To reload data")
    public void reloadFiles() throws IOException {
        schedulerService.doScheduledTask();
    }
}
