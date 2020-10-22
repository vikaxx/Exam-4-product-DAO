package ua.alevel.services;

import ua.alevel.dto.OrderItem;

import java.util.List;

public interface OrderItemPrintService {
    String printItems(List<OrderItem> orderItems);
}
