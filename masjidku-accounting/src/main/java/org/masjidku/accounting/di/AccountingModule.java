package org.masjidku.accounting.di;

import dagger.Module;
import dagger.Provides;
import org.hibernate.SessionFactory;
import org.masjidku.domain.repository.base.HibernateContext;
import org.masjidku.domain.repository.base.TransactionHelper;
import javax.inject.Singleton;

@Module
public class AccountingModule {

    @Provides
    @Singleton
    public SessionFactory provideSessionFactory() {
        return HibernateContext.getSessionFactory();
    }

    @Provides
    @Singleton
    public TransactionHelper provideTransactionHelper() {
        return HibernateContext.getTransactionHelper();
    }
}
