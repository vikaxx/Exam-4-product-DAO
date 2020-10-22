package ua.alevel.dao.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ua.alevel.dao.OrderItemDao;
import ua.alevel.datasource.DataSource;
import ua.alevel.dto.OrderItem;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
public class OrderItemDaoImpl implements OrderItemDao {
    private static final Logger LOG = LoggerFactory.getLogger(CustomerOrderDaoImpl.class);

    @Autowired
    private DataSource dataSource;

    public static final String SELECT_TOP_ITEMS = "SELECT oi.*, sum(oi.quantity) as SUMquantity " +
            "FROM orderItem oi join customerOrder co " +
            "on oi.customerOrderId = co.id where co.creationDate > ? " +
            "and co.creationDate < ? group by oi.productSku " +
            "order by SUMquantity desc limit ?";

    @Override
    public List<OrderItem> selectTopItemsByPeriod(Date start, Date end, int limit) {
        List<OrderItem> result = new ArrayList<>();
        try (final Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(SELECT_TOP_ITEMS)) {

            statement.setDate(1, new java.sql.Date(start.getTime()));
            statement.setDate(2, new java.sql.Date(end.getTime()));
            statement.setInt(3, limit);

            final ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                result.add(mapResultSetToOrderItem(resultSet));
            }

        } catch (Exception e) {
            LOG.error("", e);
        }
        return result;
    }

    private OrderItem mapResultSetToOrderItem(ResultSet resultSet){
        OrderItem orderItem = new OrderItem();

//        productSku VARCHAR(10) NOT NULL,
//        quantity INT NOT NULL,
//        productPrice INT NOT NULL,
//        customerOrderId INT NOT NULL,

        try {
            orderItem.setCustomerId(resultSet.getInt("customerOrderId"));
            orderItem.setId(resultSet.getInt("id"));
            orderItem.setProductPrice(resultSet.getInt("productPrice"));
            orderItem.setQuantity(resultSet.getInt("SUMquantity"));
            orderItem.setSku(resultSet.getString("productSku"));
        } catch (SQLException e){
            LOG.error("SQL error: ", e);
        }
        return orderItem;
    }

}
