package ua.alevel.dao;

import ua.alevel.dto.Product;

import java.util.List;

public interface ProductDao {

    boolean addNewProduct(Product product);

    boolean updateProduct(Product product);

    Product deleteProduct(String sku);

    List<Product> selectProductByCategory(String categoryName, int start);

}
