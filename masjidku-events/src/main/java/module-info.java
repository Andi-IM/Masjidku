module org.masjidku.events {
    requires transitive org.masjidku.events.client;
    requires org.masjidku.common;
    requires org.jetbrains.annotations;

    provides org.masjidku.events.client.usecase.KegiatanUseCase with org.masjidku.events.application.usecase.impl.KegiatanUseCaseImpl;
    provides org.masjidku.events.client.usecase.TamuUseCase with org.masjidku.events.application.usecase.impl.TamuUseCaseImpl;
    provides org.masjidku.events.client.usecase.TamuKegiatanUseCase with org.masjidku.events.application.usecase.impl.TamuKegiatanUseCaseImpl;
}
