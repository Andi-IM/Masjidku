package org.masjidku.domain.repository.base;

import dagger.Module;
import dagger.Provides;
import dagger.multibindings.IntoSet;
import org.masjidku.domain.entity.UserEntity;
import org.masjidku.domain.entity.UserProfileEntity;
import org.masjidku.domain.entity.UserSessionEntity;

@Module
public class CommonEntitiesModule {

    @Provides
    @IntoSet
    public Class<?> provideUserEntity() {
        return UserEntity.class;
    }

    @Provides
    @IntoSet
    public Class<?> provideUserProfileEntity() {
        return UserProfileEntity.class;
    }

    @Provides
    @IntoSet
    public Class<?> provideUserSessionEntity() {
        return UserSessionEntity.class;
    }
}
