package org.masjidku.events.domain.repository.base;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.masjidku.events.domain.entity.Kegiatan;
import org.masjidku.events.domain.entity.Tamu;
import org.masjidku.events.domain.entity.TamuKegiatan;

public class HibernateUtil {
    private static final SessionFactory sessionFactory = buildSessionFactory();

    private static SessionFactory buildSessionFactory() {
        try {
            Configuration configuration = new Configuration();
            // SQLite connection settings
            configuration.setProperty("hibernate.connection.driver_class", "org.sqlite.JDBC");
            configuration.setProperty("hibernate.connection.url", "jdbc:sqlite:masjidku.db");
            configuration.setProperty("hibernate.dialect", "org.hibernate.community.dialect.SQLiteDialect");
            configuration.setProperty("hibernate.show_sql", "true");
            configuration.setProperty("hibernate.format_sql", "true");
            configuration.setProperty("hibernate.hbm2ddl.auto", "update");

            // Register entities
            configuration.addAnnotatedClass(Kegiatan.class);
            configuration.addAnnotatedClass(Tamu.class);
            configuration.addAnnotatedClass(TamuKegiatan.class);

            return configuration.buildSessionFactory();
        } catch (Throwable ex) {
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public static void shutdown() {
        getSessionFactory().close();
    }
}
