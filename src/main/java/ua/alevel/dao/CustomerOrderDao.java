package ua.alevel.dao;

import ua.alevel.dto.CustomerOrder;

import java.util.Date;
import java.util.List;

public interface CustomerOrderDao {
    List<CustomerOrder> selectAllCustomerOrders(); // pagination !!!
    List<CustomerOrder> selectAllAfterDate(Date date);
    List<CustomerOrder> selectAllByEmployeeId(int employeeId);
    List<CustomerOrder> selectAllByCustomerName(String name);

}
