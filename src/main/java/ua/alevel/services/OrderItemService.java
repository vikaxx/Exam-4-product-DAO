package ua.alevel.services;

import ua.alevel.dto.OrderItem;

import java.util.Date;
import java.util.List;

public interface OrderItemService {
    List<OrderItem> SelectTopItems(Date start, Date end);
}
