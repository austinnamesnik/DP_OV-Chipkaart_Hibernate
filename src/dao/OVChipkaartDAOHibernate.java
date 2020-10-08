package dao;

import domein.OVChipkaart;
import domein.Reiziger;
import org.hibernate.Session;

import javax.persistence.EntityManager;
import java.util.List;

public class OVChipkaartDAOHibernate extends BaseDAOHibernate implements OVChipkaartDAO {

    public OVChipkaartDAOHibernate(Session session) {
        super(session);
    }

    @Override
    public boolean save(OVChipkaart ovChipkaart) {
        try {
            this.session.save(ovChipkaart);
            executeTransaction(EntityManager::getProperties);
            return true;
        } catch (RuntimeException e) {
            System.err.println("[SQLException] OVChipkaart-object couldn't be saved: " + e);
            return false;
        }
    }

    @Override
    public boolean update(OVChipkaart ovChipkaart) {
        try {
            this.session.update(ovChipkaart);
            executeTransaction(EntityManager::getProperties);
            return true;
        } catch (RuntimeException e) {
            System.err.println("[SQLException] OVChipkaart-object couldn't be updated: " + e);
            return false;
        }
    }

    @Override
    public boolean delete(OVChipkaart ovChipkaart) {
        try {
            this.session.delete(ovChipkaart);
            executeTransaction(EntityManager::getProperties);
            return true;
        } catch (RuntimeException e) {
            System.err.println("[SQLException] OVChipkaart-object couldn't be deleted: " + e);
            return false;
        }
    }

    @Override
    public OVChipkaart findByKaartnummer(int kaartnummer) {
        try {
            return this.session.get(OVChipkaart.class, kaartnummer);
        } catch (RuntimeException e) {
            System.err.println("[SQLException] OVChipkaart-object wasn't found: " + e);
        }
        executeTransaction(EntityManager::getProperties);
        return null;
    }

    @Override
    public List findAll() {
        return this.session.createQuery("from OVChipkaart ").list();
    }

    @Override
    public List findByReiziger(Reiziger reiziger) {
        try {
            return this.session.createQuery("SELECT ov FROM OVChipkaart ov WHERE ov.reiziger_id = :id")
                    .setParameter("id", reiziger).list();
        } catch (RuntimeException e) {
            System.err.println("[SQLException] OVChipkaart-objects weren't found: " + e);
            return null;
        }
    }
}
