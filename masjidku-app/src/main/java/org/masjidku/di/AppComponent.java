package org.masjidku.di;

import dagger.Component;
import org.hibernate.SessionFactory;
import org.masjidku.domain.repository.base.TransactionHelper;
import org.masjidku.model.session.dao.SessionManager;

import javax.inject.Singleton;

@AppScope
@Component(modules = {AppModule.class, DatabaseModule.class})
public interface AppComponent {
    SessionManager getSessionManager();
    SessionFactory getSessionFactory();
    TransactionHelper getTransactionHelper();
}
