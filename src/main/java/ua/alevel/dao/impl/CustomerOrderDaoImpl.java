package ua.alevel.dao.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ua.alevel.dao.CustomerOrderDao;
import ua.alevel.datasource.DataSource;
import ua.alevel.dto.CustomerOrder;

import java.sql.*;
import java.util.ArrayList;
//import java.util.Collections;  // Collections.emptyList();
import java.util.Date;
import java.util.List;

@Component
public class CustomerOrderDaoImpl implements CustomerOrderDao {
    private static final Logger LOG = LoggerFactory.getLogger(CustomerOrderDaoImpl.class);
    private DataSource dataSource;

    @Autowired // достать поле из конструктора который предоставляет спринг
    public CustomerOrderDaoImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public List<CustomerOrder> selectAllCustomerOrders() {
        List<CustomerOrder> customerOrders = new ArrayList<>();
        try (final Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement("Select * from customerOrder")) {
            final ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                customerOrders.add(mapResultSetToCustomerOrder(resultSet));
            }
        } catch (SQLException e) {
            LOG.error("SQL error: ", e);
        }
        return customerOrders;
    }

    private CustomerOrder mapResultSetToCustomerOrder(ResultSet resultSet) {
        CustomerOrder current = new CustomerOrder();
        try {
            current.setId(resultSet.getInt("id"));
            current.setCustomerName(resultSet.getString("customerName"));
            current.setEmployeeId(resultSet.getInt("employeeId"));
            current.setCreationDate(resultSet.getDate("creationDate"));
        } catch (SQLException e) {
            LOG.error("SQL error: ", e);
        }
        return current;
    }

    @Override
    public List<CustomerOrder> selectAllAfterDate(Date date) {
        List<CustomerOrder> customerOrders = new ArrayList<>();
        try (final Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement("Select * from customerOrder where creationDate > ?")) {

            java.sql.Date sqlDate = new java.sql.Date(date.getTime());
            statement.setDate(1, sqlDate);

            final ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                customerOrders.add(mapResultSetToCustomerOrder(resultSet));
            }
        } catch (SQLException e) {
            LOG.error("SQL error: ", e);
        }
        return customerOrders;
    }

    @Override
    public List<CustomerOrder> selectAllByEmployeeId(int employeeId) {
        List<CustomerOrder> customerOrders = new ArrayList<>();
        try (final Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement("Select * from customerOrder where employeeId = ?")) {

            statement.setInt(1, employeeId);

            final ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                customerOrders.add(mapResultSetToCustomerOrder(resultSet));
            }
        } catch (SQLException e) {
            LOG.error("SQL error: ", e);
        }
        return customerOrders;
    }

    @Override
    public List<CustomerOrder> selectAllByCustomerName(String name) {
        List<CustomerOrder> customerOrders = new ArrayList<>();
        try (final Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement("Select * from customerOrder where customerName = ?")) {

            statement.setString(1, name);

            final ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                customerOrders.add(mapResultSetToCustomerOrder(resultSet));
            }
        } catch (SQLException e) {
            LOG.error("SQL error: ", e);
        }
        return customerOrders;
    }
}
