package ua.alevel.services;

import ua.alevel.dto.Product;

import java.util.List;

public interface ProductService {
    boolean addNewProduct(Product product);

    boolean updateProduct(Product product);

    Product deleteProduct(String sku);

    List<Product> selectProductByCategory(String categoryName, int start);
}
