package dao;

import domein.Adres;
import domein.Reiziger;
import org.hibernate.Session;

import javax.persistence.EntityManager;
import java.util.List;

public class AdresDAOHibernate extends BaseDAOHibernate implements AdresDAO {

    public AdresDAOHibernate(Session session) {
        super(session);
    }

    @Override
    public boolean save(Adres adres) {
        try {
            this.session.save(adres);
            executeTransaction(EntityManager::getProperties);
            return true;
        } catch (RuntimeException e) {
            System.err.println("[SQLException] Adres-object couldn't be saved: " + e);
            return false;
        }
    }

    @Override
    public boolean update(Adres adres) {
        try {
            this.session.update(adres);
            executeTransaction(EntityManager::getProperties);
            return true;
        } catch (RuntimeException e) {
            System.err.println("[SQLException] Adres-object couldn't be updated: " + e);
            return false;
        }
    }

    @Override
    public boolean delete(Adres adres) {
        try {
            this.session.delete(adres);
            executeTransaction(EntityManager::getProperties);
            return true;
        } catch (RuntimeException e) {
            System.err.println("[SQLException] Adres-object couldn't be deleted: " + e);
            return false;
        }
    }

    @Override
    public Adres findByReiziger(Reiziger reiziger) {
        try {
            return (Adres) this.session.createQuery("SELECT ad FROM Adres ad WHERE ad.reiziger_id = :nr")
                    .setParameter("nr", reiziger).list().get(0);
        } catch (RuntimeException e) {
            System.err.println("[SQLException] Adres-object wasn't found: " + e);
            return null;
        }
    }

    @Override
    public Adres findById(int id) {
        try {
            return this.session.get(Adres.class, id);
        } catch (RuntimeException e) {
            System.err.println("[SQLException] Adres-object wasn't found: " + e);
        }
        executeTransaction(EntityManager::getProperties);
        return null;
    }

    @Override
    public List findAll() {
        return this.session.createQuery("from Adres").list();
    }
}
