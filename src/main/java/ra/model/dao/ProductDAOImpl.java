package ra.model.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ra.model.entity.Product;

import java.util.ArrayList;
import java.util.List;

@Repository
public class ProductDAOImpl implements ProductDAO {
    @Autowired
     SessionFactory sessionFactory;

    @Override
    public List<Product> findAll() {
        Session session = sessionFactory.openSession();
        List<Product> products = new ArrayList<>();
        try {
            products = session.createQuery("from Product ",Product.class).list();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return products;
    }

    @Override
    public Product findById(Integer id) {
        Session session=sessionFactory.openSession();
        try{
            Product product=session.get(Product.class,id);
            return product;
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            session.close();
        }
        return null;
    }

    @Override
    public Boolean deleteById(Integer id) {
        Session session=sessionFactory.openSession();
        try{
            session.beginTransaction();
            session.delete(findById(id));
            session.getTransaction().commit();
            return true;
        }catch (Exception e){
            e.printStackTrace();
            session.getTransaction().rollback();
        }finally {
            session.close();
        }
        return false;
    }

    @Override
    public Boolean saveOrUpdate(Product product) {
        Session session = sessionFactory.openSession();
        try{
            session.beginTransaction();
            session.saveOrUpdate(product);
            session.getTransaction().commit();
            return true;
        }catch (Exception e){
            e.printStackTrace();
            session.getTransaction().rollback();
        }finally {
            session.close();
        }
        return false;
    }
}
