package org.masjidku.accounting.di;

import dagger.Module;
import dagger.Provides;
import dagger.multibindings.IntoSet;
import org.masjidku.accounting.domain.entity.*;

@Module
public class AccountingEntitiesModule {

    @Provides
    @IntoSet
    public Class<?> provideZakatMasukEntity() {
        return ZakatMasukEntity.class;
    }

    @Provides
    @IntoSet
    public Class<?> provideZakatKeluarEntity() {
        return ZakatKeluarEntity.class;
    }

    @Provides
    @IntoSet
    public Class<?> provideTpaMasukEntity() {
        return TpaMasukEntity.class;
    }

    @Provides
    @IntoSet
    public Class<?> provideTpaKeluarEntity() {
        return TpaKeluarEntity.class;
    }

    @Provides
    @IntoSet
    public Class<?> provideDonasiPembangunanEntity() {
        return DonasiPembangunanEntity.class;
    }

    @Provides
    @IntoSet
    public Class<?> providePembangunanEntity() {
        return PembangunanEntity.class;
    }

    @Provides
    @IntoSet
    public Class<?> provideDonasiOperasionalEntity() {
        return DonasiOperasionalEntity.class;
    }

    @Provides
    @IntoSet
    public Class<?> provideOperasionalEntity() {
        return OperasionalEntity.class;
    }

    @Provides
    @IntoSet
    public Class<?> provideDonasiAnakYatimEntity() {
        return DonasiAnakYatimEntity.class;
    }

    @Provides
    @IntoSet
    public Class<?> provideAnakYatimEntity() {
        return AnakYatimEntity.class;
    }
}
