package ra.model.dao;

import ra.model.entity.Customer;
import ra.model.entity.Product;

import java.util.List;

public interface ProductDAO {
    List<Product> findAll();
    Product findById(Integer id);
    Boolean deleteById(Integer id);
    Boolean saveOrUpdate(Product product);
}
