package ua.alevel.services;

import com.mysql.cj.util.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import ua.alevel.dto.OrderItem;

import java.util.Collections;
import java.util.Date;
import java.util.List;

@Service
public class OrderItemPrintServiceImpl implements OrderItemPrintService {
    @Override
    public String printItems(List<OrderItem> orderItems) {
        if (orderItems == null || orderItems.size() < 1){
            return "";
        }

        StringBuilder result = new StringBuilder();
        result.append("Product,Count,Price\n");

        for(OrderItem item : orderItems){
            result.append(item.getSku()+","+item.getQuantity()+","+item.getProductPrice()+"\n");
        }

        return result.toString();
    }
}
