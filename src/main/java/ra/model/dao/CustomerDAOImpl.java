package ra.model.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ra.model.entity.Customer;

import java.util.ArrayList;
import java.util.List;

@Repository
public class CustomerDAOImpl implements CustomerDAO {
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<Customer> findAll() {
        Session session = sessionFactory.openSession();
        List<Customer> customerList = new ArrayList<>();
        try {
            customerList = session.createQuery("from Customer ").list();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return customerList;
    }

    @Override
    public Customer findById(Long id) {
        Session session = sessionFactory.openSession();
        Customer customer;
        try {
            customer = session.get(Customer.class, id);
            return customer;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return null;
    }

    @Override
    public Boolean deleteById(Long id) {
        Session session = sessionFactory.openSession();
        try {
            session.beginTransaction();
            session.delete(findById(id));
            session.getTransaction().commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
        return false;
    }


    @Override
    public Boolean saveOrUpdate(Customer customer) {
        Session session = sessionFactory.openSession();
        try {
            session.beginTransaction();
            session.saveOrUpdate(customer);
            session.getTransaction().commit();
            return true;
        } catch (Exception e) {
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
        return false;
    }
}
