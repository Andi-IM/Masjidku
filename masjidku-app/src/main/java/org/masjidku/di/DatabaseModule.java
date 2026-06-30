package org.masjidku.di;

import dagger.Module;
import org.masjidku.auth.domain.repository.base.CommonEntitiesModule;
import dagger.Provides;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.masjidku.accounting.di.AccountingEntitiesModule;
import org.masjidku.events.di.EventsEntitiesModule;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Singleton;
import java.util.Set;

@Module(includes = {CommonEntitiesModule.class, EventsEntitiesModule.class, AccountingEntitiesModule.class})
public class DatabaseModule {
    private static final Logger log = LoggerFactory.getLogger(DatabaseModule.class);

    @Provides
    @Singleton
    public SessionFactory provideSessionFactory(Set<Class<?>> entityClasses) {
        try {
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

            // Register entities from Multibindings
            for (Class<?> entityClass : entityClasses) {
                configuration.addAnnotatedClass(entityClass);
            }

            return configuration.buildSessionFactory();
        } catch (Exception ex) {
            log.error("SessionFactory creation failed.", ex);
            throw new ExceptionInInitializerError(ex);
        }
    }
}
