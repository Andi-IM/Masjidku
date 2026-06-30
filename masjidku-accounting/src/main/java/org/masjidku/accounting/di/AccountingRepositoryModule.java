package org.masjidku.accounting.di;

import dagger.Binds;
import dagger.Module;
import org.masjidku.accounting.domain.repository.*;
import org.masjidku.accounting.domain.repository.impl.*;

@Module
public interface AccountingRepositoryModule {

    @Binds
    ZakatMasukRepository bindZakatMasukRepository(ZakatMasukRepositoryImpl impl);

    @Binds
    ZakatKeluarRepository bindZakatKeluarRepository(ZakatKeluarRepositoryImpl impl);

    @Binds
    TpaMasukRepository bindTpaMasukRepository(TpaMasukRepositoryImpl impl);

    @Binds
    TpaKeluarRepository bindTpaKeluarRepository(TpaKeluarRepositoryImpl impl);

    @Binds
    DonasiPembangunanRepository bindDonasiPembangunanRepository(DonasiPembangunanRepositoryImpl impl);

    @Binds
    PembangunanRepository bindPembangunanRepository(PembangunanRepositoryImpl impl);

    @Binds
    DonasiOperasionalRepository bindDonasiOperasionalRepository(DonasiOperasionalRepositoryImpl impl);

    @Binds
    OperasionalRepository bindOperasionalRepository(OperasionalRepositoryImpl impl);

    @Binds
    DonasiAnakYatimRepository bindDonasiAnakYatimRepository(DonasiAnakYatimRepositoryImpl impl);

    @Binds
    AnakYatimRepository bindAnakYatimRepository(AnakYatimRepositoryImpl impl);
}
