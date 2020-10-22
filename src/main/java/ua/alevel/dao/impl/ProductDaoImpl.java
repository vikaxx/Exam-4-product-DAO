package ua.alevel.dao.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ua.alevel.dao.ProductDao;
import ua.alevel.datasource.DataSource;
import ua.alevel.dto.Product;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class ProductDaoImpl implements ProductDao {
    private static final Logger LOG = LoggerFactory.getLogger(ProductDaoImpl.class);
    private final int DEFAULT_QUANTITY = 50;
    private DataSource dataSource;

    @Autowired // достать поле из конструктора который предоставляет спринг
    public ProductDaoImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public boolean addNewProduct(Product product) {
        try (final Connection connection = dataSource.getConnection();
             PreparedStatement ps = connection.prepareStatement("INSERT INTO " +
                     "product (sku, name, price, description, categoryName, supplierId) " +
                     "VALUES(?, ?, ?, ?, ?, ?)")) {

            ps.setString(1, product.getSku());
            ps.setString(2, product.getName());
            ps.setInt(3, product.getPrice());
            ps.setString(4, product.getDescription());
            ps.setString(5, product.getCategoryName());
            ps.setInt(6, product.getSupplierId());

            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            LOG.error("SQL Error", e);
        }
        return false;
    }

    @Override
    public boolean updateProduct(Product product) {
        try (final Connection connection = dataSource.getConnection();
             PreparedStatement ps = connection.prepareStatement("UPDATE product " +
                     "SET name = ?, price = ?, description = ?, categoryName = ?, supplierId = ? " +
                     "WHERE sku = ?")) {

            ps.setString(1, product.getName());
            ps.setInt(2, product.getPrice());
            ps.setString(3, product.getDescription());
            ps.setString(4, product.getCategoryName());
            ps.setInt(5, product.getSupplierId());
            ps.setString(6, product.getSku());

            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            LOG.error("SQL Error", e);
        }
        return false;
    }

    @Override
    public Product deleteProduct(String sku) {
        Product deletedProduct = new Product();
        try (final Connection connection = dataSource.getConnection();
             PreparedStatement ps = connection.prepareStatement("DELETE FROM product WHERE sku = ?")) {

            Statement statement = connection.createStatement();

            String query = "SELECT * FROM product WHERE sku = \"" + sku + "\"";
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                deletedProduct = mapResultSetToProduct(resultSet);
            }

            ps.setString(1, sku);
            ps.executeUpdate();

        } catch (SQLException e) {
            LOG.error("SQL Error", e);
        }
        return deletedProduct;
    }

    @Override
    public List<Product> selectProductByCategory(String categoryName, int start) {
        List<Product> result = new ArrayList<>();
        try (final Connection connection = dataSource.getConnection();
             PreparedStatement ps = connection.prepareStatement("SELECT * FROM product WHERE categoryName = ? LIMIT ?, ?")) {

            ps.setString(1, categoryName);
            ps.setInt(2, start);
            ps.setInt(3, DEFAULT_QUANTITY);

            final ResultSet resultSet = ps.executeQuery();

            while (resultSet.next()) {
                result.add(mapResultSetToProduct(resultSet));
            }
        } catch (SQLException e) {
            LOG.error("SQL Error", e);
        }
        return result;
    }

    private Product mapResultSetToProduct(ResultSet resultSet) {
        Product current = new Product();
        try {
            current.setSku(resultSet.getString("sku"));
            current.setName(resultSet.getString("name"));
            current.setPrice(resultSet.getInt("price"));
            current.setSupplierId(resultSet.getInt("supplierId"));
            current.setDescription(resultSet.getString("description"));
            current.setCategoryName(resultSet.getString("categoryName"));
        } catch (SQLException e) {
            LOG.error("SQL error: ", e);
        }
        return current;
    }

}

