package ra.model.service;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ra.model.dao.CustomerDAO;
import ra.model.entity.Customer;

import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {
    @Autowired
    private CustomerDAO customerDAO;

    @Override
    public List<Customer> findAll() {
        return customerDAO.findAll();
    }

    @Override
    public Customer findById(Long id) {
        return customerDAO.findById(id);
    }

    @Override
    public void deleteById(Long id) {
        customerDAO.deleteById(id);
    }

    @Override
    public Boolean saveOrUpdate(Customer customer) {
        return customerDAO.saveOrUpdate(customer);
    }
}
