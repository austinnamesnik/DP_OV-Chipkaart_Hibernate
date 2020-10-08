package dao;


import domein.OVChipkaart;
import domein.Product;
import org.hibernate.Session;

import javax.persistence.EntityManager;
import java.util.List;

public class ProductDAOHibernate extends BaseDAOHibernate implements ProductDAO {

    public ProductDAOHibernate(Session session) {
        super(session);
    }

    @Override
    public boolean save(Product product) {
        try {
            this.session.save(product);
            executeTransaction(EntityManager::getProperties);
            return true;
        } catch (RuntimeException e) {
            System.err.println("[SQLException] Product-object couldn't be saved: " + e);
            return false;
        }
    }

    @Override
    public boolean update(Product product) {
        try {
            this.session.update(product);
            executeTransaction(EntityManager::getProperties);
            return true;
        } catch (RuntimeException e) {
            System.err.println("[SQLException] Product-object couldn't be updated: " + e);
            return false;
        }
    }

    @Override
    public boolean delete(Product product) {
        try {
            this.session.delete(product);
            executeTransaction(EntityManager::getProperties);
            return true;
        } catch (RuntimeException e) {
            System.err.println("[SQLException] Reiziger-object couldn't be deleted: " + e);
            return false;
        }
    }

    @Override
    public List findByOVChipkaart(OVChipkaart ovChipkaart) {
        try {
            return this.session.createNativeQuery("SELECT * FROM product WHERE product_nummer IN (SELECT product_nummer FROM ov_chipkaart_product WHERE kaart_nummer =:nmr)", Product.class).setParameter("nmr", ovChipkaart).getResultList();
        } catch (RuntimeException e) {
            System.err.println("[SQLException] Reiziger-object wasn't found: " + e);
            return null;
        }
    }

    @Override
    public List findAll() {
        return this.session.createQuery("from Product ").getResultList();
    }

    @Override
    public Product findByID(int id) {
        try {
            return this.session.get(Product.class, id);
        } catch (RuntimeException e) {
            System.err.println("[SQLException] OVChipkaart-object wasn't found: " + e);
        }
        executeTransaction(EntityManager::getProperties);
        return null;
    }
}
