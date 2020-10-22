package ua.alevel.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.alevel.dao.ProductDao;
import ua.alevel.dto.Product;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductDao productDao;

    @Override
    public boolean addNewProduct(Product product) {
        return productDao.addNewProduct(product);
    }

    @Override
    public boolean updateProduct(Product product) {
        return productDao.updateProduct(product);
    }

    @Override
    public Product deleteProduct(String sku) {
        return productDao.deleteProduct(sku);
    }

    @Override
    public List<Product> selectProductByCategory(String categoryName, int start) {
        return productDao.selectProductByCategory(categoryName, start);
    }
}
