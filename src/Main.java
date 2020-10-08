import dao.*;
import domein.Adres;
import domein.OVChipkaart;
import domein.Reiziger;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.Session;
import org.hibernate.Query;
import org.hibernate.cfg.Configuration;
import org.hibernate.metadata.ClassMetadata;

import javax.persistence.metamodel.EntityType;
import javax.persistence.metamodel.Metamodel;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * Testklasse - deze klasse test alle andere klassen in deze package.
 *
 * System.out.println() is alleen in deze klasse toegestaan (behalve voor exceptions).
 *
 * @author tijmen.muller@hu.nl
 */
public class Main {
    // Creëer een factory voor Hibernate sessions.
    private static final SessionFactory factory;

    static {
        try {
            // Create a Hibernate session factory
            factory = new Configuration().configure().buildSessionFactory();
        } catch (Throwable ex) {
            throw new ExceptionInInitializerError(ex);
        }
    }

    /**
     * Retouneer een Hibernate session.
     *
     * @return Hibernate session
     * @throws HibernateException
     */
    private static Session getSession() throws HibernateException {
        return factory.openSession();
    }

    public static void main(String[] args) throws SQLException {
        //testFetchAll();
        testDAOHibernate();
    }

    /**
     * P6. Haal alle (geannoteerde) entiteiten uit de database.
     */
    private static void testFetchAll() {
        try (Session session = getSession()) {
            Metamodel metamodel = session.getSessionFactory().getMetamodel();
            for (EntityType<?> entityType : metamodel.getEntities()) {
                Query query = session.createQuery("from " + entityType.getName());

                System.out.println("[Test] Alle objecten van type " + entityType.getName() + " uit database:");
                for (Object o : query.list()) {
                    System.out.println("  " + o);
                }
                System.out.println();
            }
        }
    }

    private static void testDAOHibernate() {
        ReizigerDAOHibernate rdaoh = new ReizigerDAOHibernate(getSession());
        AdresDAOHibernate adaoh = new AdresDAOHibernate(getSession());
        OVChipkaartDAOHibernate ovckdaoh = new OVChipkaartDAOHibernate(getSession());
        ProductDAOHibernate pdaoh = new ProductDAOHibernate(getSession());

        System.out.println(adaoh.findAll()); //Alle adressen die bekend zijn in de database
        System.out.println();
        System.out.println(adaoh.findById(1)); //Adres met ID=1 ophalen uit de database
        Reiziger r = new Reiziger(27, "AM", "", "Namesnik", Date.valueOf("2001-07-27"));
        List reizigers = rdaoh.findByGbDatum("2001-07-27"); //Reizigers met geboortedatum="2001-07-27" ophalen uit de database
        System.out.println(reizigers);
        System.out.println(adaoh.findByReiziger(r)); //Adres ophalen met reiziger=new Reiziger(27, "AM", "", "Namesnik", Date.valueOf("2001-07-27"))
        System.out.println(adaoh.findById(3)); //Adres ophalen met ID=3
        System.out.println(rdaoh.findById(3)); //Reiziger ophalen met ID=3
        List ov = ovckdaoh.findByReiziger(r); //Alle OVChipkaarten ophalen van Reiziger met ID=27
        System.out.println();
        System.out.println(ov);
        System.out.println();
        System.out.println(ovckdaoh.findByKaartnummer(90537)); //OVChipkaart ophalen met Kaartnummer=90537
        System.out.println();
        System.out.println(ovckdaoh.findAll()); //Alle OVChipkaarten uit de database ophalen
        System.out.println();
        OVChipkaart ovChipkaart = ovckdaoh.findByKaartnummer(90537);
        System.out.println(pdaoh.findByOVChipkaart(ovChipkaart)); //Alle Producten ophalen van OVChipkaart=90537
        System.out.println();
        System.out.println(pdaoh.findByID(6)); //Product met ID=3 ophalen
    }
}
