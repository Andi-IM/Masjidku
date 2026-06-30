package org.masjidku.accounting.domain.repository.base;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.masjidku.accounting.domain.entity.*;

public class HibernateUtil {
    private static final Logger log = LoggerFactory.getLogger(HibernateUtil.class);
    private static final SessionFactory sessionFactory = buildSessionFactory();
    
    private HibernateUtil(){}

    private static SessionFactory buildSessionFactory() {
        try {
            Configuration configuration = getConfiguration();

            // Register entities
            configuration.addAnnotatedClass(ZakatMasukEntity.class);
            configuration.addAnnotatedClass(ZakatKeluarEntity.class);
            configuration.addAnnotatedClass(TpaMasukEntity.class);
            configuration.addAnnotatedClass(TpaKeluarEntity.class);
            configuration.addAnnotatedClass(DonasiPembangunanEntity.class);
            configuration.addAnnotatedClass(PembangunanEntity.class);
            configuration.addAnnotatedClass(DonasiOperasionalEntity.class);
            configuration.addAnnotatedClass(OperasionalEntity.class);
            configuration.addAnnotatedClass(DonasiAnakYatimEntity.class);
            configuration.addAnnotatedClass(AnakYatimEntity.class);

            return configuration.buildSessionFactory();
        } catch (Exception ex) {
            log.error("Initial SessionFactory creation failed.", ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    private static @NotNull Configuration getConfiguration() {
        Configuration configuration = new Configuration();
        // SQLite connection settings
        configuration.setProperty("hibernate.connection.driver_class", "org.sqlite.JDBC");
        configuration.setProperty("hibernate.connection.url", "jdbc:sqlite:masjidku.db");
        configuration.setProperty("hibernate.dialect", "org.hibernate.community.dialect.SQLiteDialect");
        configuration.setProperty("hibernate.show_sql", "true");
        configuration.setProperty("hibernate.format_sql", "true");
        configuration.setProperty("hibernate.hbm2ddl.auto", "update");
        
        // HikariCP settings
        configuration.setProperty("hibernate.connection.provider_class", "org.hibernate.hikaricp.internal.HikariCPConnectionProvider");
        configuration.setProperty("hibernate.hikari.connectionTimeout", "20000");
        configuration.setProperty("hibernate.hikari.minimumIdle", "1");
        configuration.setProperty("hibernate.hikari.maximumPoolSize", "5");
        configuration.setProperty("hibernate.hikari.idleTimeout", "30000");

        // Contextual Session
        configuration.setProperty("hibernate.current_session_context_class", "thread");
        
        return configuration;
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public static void shutdown() {
        getSessionFactory().close();
    }

    public static <T> T executeInTransaction(java.util.function.Supplier<T> action) {
        org.hibernate.Session session = getSessionFactory().getCurrentSession();
        org.hibernate.Transaction tx = session.getTransaction();
        boolean isNewTransaction = false;

        if (!tx.isActive()) {
            tx = session.beginTransaction();
            isNewTransaction = true;
        }

        try {
            T result = action.get();
            if (isNewTransaction) {
                tx.commit();
            }
            return result;
        } catch (RuntimeException e) {
            if (isNewTransaction && tx.isActive()) {
                tx.rollback();
            }
            throw e;
        }
    }

    public static void executeInTransaction(Runnable action) {
        executeInTransaction(() -> {
            action.run();
            return null;
        });
    }
}
