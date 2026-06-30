package org.masjidku.accounting.di;

import dagger.Component;
import org.masjidku.accounting.service.impl.AccountingClientImpl;
import javax.inject.Singleton;

@Singleton
@Component(modules = {AccountingRepositoryModule.class, AccountingModule.class})
public interface AccountingComponent {
    AccountingClientImpl getAccountingClientImpl();
}
