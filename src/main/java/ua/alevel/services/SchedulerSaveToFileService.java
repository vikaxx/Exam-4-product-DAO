package ua.alevel.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ua.alevel.dto.OrderItem;
import ua.alevel.services.tasks.SaveTask;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Component
public class SchedulerSaveToFileService {

    @Autowired
    private OrderItemService orderItemService;

    @Autowired
    private OrderItemPrintService printService;

    @Autowired
    private FileSaverService fileSaverService;

    public void runScheduler(){
        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(1);

        scheduledExecutorService.scheduleWithFixedDelay(new SaveTask(orderItemService,printService,fileSaverService),0,31, TimeUnit.DAYS);
    }
}
