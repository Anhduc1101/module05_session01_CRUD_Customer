package ra.model.service;

import ra.model.entity.Customer;

import java.util.List;

public interface CustomerService {
    List<Customer> findAll();
    Customer findById(Long id);
    void deleteById(Long id);
    Boolean saveOrUpdate(Customer customer);
}
