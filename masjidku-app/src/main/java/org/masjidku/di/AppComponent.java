package org.masjidku.di;

import dagger.Component;
import org.hibernate.SessionFactory;
import org.masjidku.domain.repository.base.TransactionHelper;
import org.masjidku.util.SessionManager;

import javax.inject.Singleton;

@Singleton
@Component(modules = {AppModule.class, DatabaseModule.class})
public interface AppComponent {
    SessionManager getSessionManager();
    SessionFactory getSessionFactory();
    TransactionHelper getTransactionHelper();
}
