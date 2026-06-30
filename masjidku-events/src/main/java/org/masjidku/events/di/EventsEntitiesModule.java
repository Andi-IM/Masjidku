package org.masjidku.events.di;

import dagger.Module;
import dagger.Provides;
import dagger.multibindings.IntoSet;
import org.masjidku.events.domain.entity.Kegiatan;
import org.masjidku.events.domain.entity.Tamu;
import org.masjidku.events.domain.entity.TamuKegiatan;

@Module
public class EventsEntitiesModule {

    @Provides
    @IntoSet
    public Class<?> provideKegiatanEntity() {
        return Kegiatan.class;
    }

    @Provides
    @IntoSet
    public Class<?> provideTamuEntity() {
        return Tamu.class;
    }

    @Provides
    @IntoSet
    public Class<?> provideTamuKegiatanEntity() {
        return TamuKegiatan.class;
    }
}
