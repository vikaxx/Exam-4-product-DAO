package ua.alevel.dao;

import ua.alevel.dto.OrderItem;

import java.util.Date;
import java.util.List;

public interface OrderItemDao {
    List<OrderItem> selectTopItemsByPeriod(Date start, Date end, int limit);
}
