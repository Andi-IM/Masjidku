package org.masjidku.di;

import dagger.Module;
import dagger.Provides;
import org.masjidku.domain.repository.UserSessionRepository;
import org.masjidku.domain.repository.impl.UserSessionRepositoryImpl;

import javax.inject.Singleton;

@Module
public class AppModule {

    @Provides
    @Singleton
    public UserSessionRepository provideUserSessionRepository() {
        return new UserSessionRepositoryImpl();
    }
}
