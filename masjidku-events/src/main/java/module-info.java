import org.masjidku.events.application.EventsClientImpl;
import org.masjidku.events.application.usecase.KegiatanUseCase;
import org.masjidku.events.application.usecase.TamuKegiatanUseCase;
import org.masjidku.events.application.usecase.TamuUseCase;

module org.masjidku.events {
    requires transitive org.masjidku.events.client;
    requires org.masjidku.common;
    requires org.jetbrains.annotations;
    requires org.hibernate.orm.core;
    requires jakarta.persistence;
    requires java.naming;

    provides KegiatanUseCase with org.masjidku.events.application.usecase.impl.KegiatanUseCaseImpl;
    provides TamuUseCase with org.masjidku.events.application.usecase.impl.TamuUseCaseImpl;
    provides TamuKegiatanUseCase with org.masjidku.events.application.usecase.impl.TamuKegiatanUseCaseImpl;
    provides org.masjidku.events.client.EventsClient with EventsClientImpl;
}
