package dao;

import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.function.Consumer;

public class BaseDAOHibernate {
    Session session;

    public BaseDAOHibernate(Session session) {
        this.session = session;
    }

    protected void executeTransaction(Consumer<Session> action) {
        Transaction t = this.session.getTransaction();
        try {
            t.begin();
            action.accept(this.session);
            t.commit();
        } catch (RuntimeException e) {
            System.err.println("[Transaction] Transaction failed: " + e);
            t.rollback();
        }
    }
}
