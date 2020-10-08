package dao;

import domein.Reiziger;
import org.hibernate.Session;

import javax.persistence.EntityManager;
import java.sql.Date;
import java.util.List;

public class ReizigerDAOHibernate extends BaseDAOHibernate implements ReizigerDAO {


    public ReizigerDAOHibernate(Session session) {
        super(session);
    }

    @Override
    public boolean save(Reiziger reiziger) {
        try {
            this.session.save(reiziger);
            executeTransaction(EntityManager::getProperties);
            return true;
        } catch (RuntimeException e) {
            System.err.println("[SQLException] Reiziger-object couldn't be saved: " + e);
            return false;
        }
    }

    @Override
    public boolean update(Reiziger reiziger) {
        try {
            this.session.update(reiziger);
            executeTransaction(EntityManager::getProperties);
            return true;
        } catch (RuntimeException e) {
            System.err.println("[SQLException] Reiziger-object couldn't be updated: " + e);
            return false;
        }
    }

    @Override
    public boolean delete(Reiziger reiziger) {
        try {
            this.session.delete(reiziger);
            executeTransaction(EntityManager::getProperties);
            return true;
        } catch (RuntimeException e) {
            System.err.println("[SQLException] Reiziger-object couldn't be deleted: " + e);
            return false;
        }
    }

    @Override
    public Reiziger findById(int id) {
        try {
            return this.session.get(Reiziger.class, id);
        } catch (RuntimeException e) {
            System.err.println("[SQLException] Reiziger-object wasn't found: " + e);
        }
        executeTransaction(EntityManager::getProperties);
        return null;
    }

    @Override
    public List findByGbDatum(String datum) {
        try {
            return this.session.createQuery("SELECT rz FROM Reiziger rz WHERE rz.geboortedatum = :datum")
                    .setParameter("datum", Date.valueOf(datum)).list();
        } catch (RuntimeException e) {
            System.err.println("[SQLException] Reiziger-object wasn't found: " + e);
            return null;
        }
    }

    @Override
    public List findAll() {
        return this.session.createQuery("from Reiziger ").list();
    }
}
