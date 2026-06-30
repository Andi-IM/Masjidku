package org.masjidku.di;

import dagger.Module;
import dagger.Provides;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.masjidku.auth.client.AuthClient;
import org.masjidku.auth.domain.service.AuthClientImpl;
import org.masjidku.reporting.client.service.ReportService;
import org.masjidku.util.ServiceProvider;

import javax.inject.Singleton;

@Module
public class AppModule {

    @Provides
    @Singleton
    public AuthClient provideAuthClient() {
        return new AuthClientImpl();
    }

    @Provides
    @Singleton
    public ReportService provideReportService(SessionFactory sessionFactory) {
        ReportService service = ServiceProvider.get(ReportService.class);
        service.setConnectionProvider(action -> {
            try (Session session = sessionFactory.openSession()) {
                session.doWork(action::accept);
            }
        });
        return service;
    }
}
