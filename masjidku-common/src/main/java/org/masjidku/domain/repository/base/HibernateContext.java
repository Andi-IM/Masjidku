package org.masjidku.domain.repository.base;

import org.hibernate.SessionFactory;

public class HibernateContext {
    private static SessionFactory sessionFactory;
    private static TransactionHelper transactionHelper;

    private HibernateContext(){}

    public static void initialize(SessionFactory sf, TransactionHelper th) {
        sessionFactory = sf;
        transactionHelper = th;
    }

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) throw new IllegalStateException("HibernateContext not initialized. Call initialize() first.");
        return sessionFactory;
    }

    public static TransactionHelper getTransactionHelper() {
        if (transactionHelper == null) throw new IllegalStateException("HibernateContext not initialized.");
        return transactionHelper;
    }
}
