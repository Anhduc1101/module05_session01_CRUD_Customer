package ra.model.dao;

import ra.model.entity.Customer;

import java.util.List;

public interface CustomerDAO {
    List<Customer> findAll();
    Customer findById(Long id);
    Boolean deleteById(Long id);
    Boolean saveOrUpdate(Customer customer);
}
