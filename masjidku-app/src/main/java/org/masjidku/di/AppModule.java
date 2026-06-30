package org.masjidku.di;

import dagger.Module;
import dagger.Provides;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.masjidku.domain.repository.UserSessionRepository;
import org.masjidku.domain.repository.impl.UserSessionRepositoryImpl;
import org.masjidku.reporting.client.service.ReportService;
import org.masjidku.util.ServiceProvider;

import javax.inject.Singleton;

@Module
public class AppModule {

    @Provides
    @Singleton
    public UserSessionRepository provideUserSessionRepository() {
        return new UserSessionRepositoryImpl();
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
