package ra.model.service;

import ra.model.entity.Customer;
import ra.model.entity.Product;

import java.util.List;

public interface ProductService {
    List<Product> findAll();
    Product findById(Integer id);
    void deleteById(Integer id);
    Boolean saveOrUpdate(Product product);
}
