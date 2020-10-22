package ua.alevel;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import ua.alevel.dao.CustomerOrderDao;
import ua.alevel.dao.ProductDao;
import ua.alevel.dao.impl.CustomerOrderDaoImpl;
import ua.alevel.dao.impl.ProductDaoImpl;
import ua.alevel.datasource.HikariDataSource;
import ua.alevel.dto.OrderItem;
import ua.alevel.dto.Product;
import ua.alevel.services.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

@SpringBootApplication
public class Main {
    public static final Logger LOG = LoggerFactory.getLogger(Main.class);
    static @Value("${db.url}")
    String url;
    static @Value("${db.user}")
    String userName;
    static @Value("${db.password}")
    String pass;

    public static void main(String[] args) {
//        Date utilDate = new Date();
//        System.out.println(utilDate.toString());
////        System.out.println(utilDate.getTime());
//        java.sql.Date sqlDate;
//        sqlDate = new java.sql.Date(utilDate.getTime());
//        System.out.println(sqlDate.toString());
        final ConfigurableApplicationContext context = SpringApplication.run(Main.class, args);
        final ConfigurableListableBeanFactory beanFactory = context.getBeanFactory();

//        SchedulerSaveToFileService scheduler = beanFactory.getBean(SchedulerSaveToFileService.class);
//        scheduler.runScheduler();
//
////        try {
////            System.out.println(12/0);
////        } catch (Exception e) {
////            LOG.error("Smth was happened", e);
////        }
//        System.out.println("input start date period, please ( format yyyy-mm-dd ) : ");
//        Scanner in = new Scanner(System.in);
//
//        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
//        Date startDate = getDatePeriod(in, dateFormat);
//        System.out.println("input end date period, please ( format yyyy-mm-dd ) : ");
//
//        Date finalDate = getDatePeriod(in, dateFormat);
//        if (finalDate.after(startDate)) {
//            System.out.println("Generation...");
//
//            final OrderItemService orderItemService = beanFactory.getBean(OrderItemService.class);
//
//            List<OrderItem> orderItems = orderItemService.SelectTopItems(startDate,finalDate);
//            OrderItemPrintService printService = beanFactory.getBean(OrderItemPrintService.class);
//            final String csvText = printService.printItems(orderItems);
//            FileSaverService saveService = beanFactory.getBean(FileSaverService.class);
//
//            saveService.saveToFile("result.csv", csvText);
//
//
//        } else {
//            System.out.println("incorrect end date input.");
//            finalDate = getDatePeriod(in, dateFormat);
//        }
//
////        System.out.println(startDate + "\t " + finalDate);
//
//        final OrderItemService orderItemService = beanFactory.getBean(OrderItemService.class);
//        List<OrderItem> orderItems = orderItemService.SelectTopItems(startDate, finalDate);
//        for (OrderItem orderItem : orderItems) {
//            System.out.println(orderItem);
//        }

//        Product testProduct = new Product("sku123", "skuuu", 123, "sku123", "Salt", 74);
        ProductService productService = beanFactory.getBean(ProductService.class);
//        System.out.println(productService.addNewProduct(testProduct));
        List<Product> products = new ArrayList<>();
        products = productService.selectProductByCategory("Salt", 5);
        products.forEach(System.out::println);

    }

    private static Date getDatePeriod(Scanner in, SimpleDateFormat dateFormat) {
        String dateLine = in.nextLine();
        Date date;
        while (true) {
            try {
                date = dateFormat.parse(dateLine);
                return date;
            } catch (ParseException e) {
                LOG.error("Can not parse date " + dateLine, e);
                System.out.println("incorrect data, try again.");
            }
        }
    }
}
