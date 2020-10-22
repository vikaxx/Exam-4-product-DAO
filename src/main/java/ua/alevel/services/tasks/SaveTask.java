package ua.alevel.services.tasks;

import org.springframework.beans.factory.annotation.Autowired;
import ua.alevel.dto.OrderItem;
import ua.alevel.services.FileSaverService;
import ua.alevel.services.OrderItemPrintService;
import ua.alevel.services.OrderItemService;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class SaveTask implements Runnable {
    private OrderItemPrintService printService;
    private FileSaverService fileSaverService;
    private OrderItemService orderItemService;

    public SaveTask(OrderItemService orderItemService, OrderItemPrintService printService, FileSaverService fileSaverService) {
        this.printService = printService;
        this.fileSaverService = fileSaverService;
        this.orderItemService  = orderItemService;
    }

    private SaveTask() {

    }

    @Override
    public void run() {
        Date currentDate = new Date();
        Calendar calendar = Calendar.getInstance();

        calendar.setTime(currentDate);

        calendar.add(Calendar.MONTH, -1);

        final Date startDate = calendar.getTime();
        List<OrderItem> orderItems = orderItemService.SelectTopItems(startDate, currentDate);

        final String csv = printService.printItems(orderItems);

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        final String fileName = "top-"+dateFormat.format(currentDate)+".csv";
        fileSaverService.saveToFile(fileName,csv);
    }

}
