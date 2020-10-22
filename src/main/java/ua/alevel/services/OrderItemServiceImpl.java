package ua.alevel.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.alevel.dao.OrderItemDao;
import ua.alevel.dto.OrderItem;

import java.util.Date;
import java.util.List;

@Service
public class OrderItemServiceImpl implements OrderItemService {
    public static final int DEFAULT_LIMIT = 100;

    @Autowired
    private OrderItemDao orderItemDao;

    @Override
    public List<OrderItem> SelectTopItems(Date start, Date end) {
        return orderItemDao.selectTopItemsByPeriod(start, end, DEFAULT_LIMIT);
    }
}
