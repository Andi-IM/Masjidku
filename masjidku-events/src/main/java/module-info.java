import org.masjidku.events.application.EventsClientImpl;

module org.masjidku.events {
    requires transitive org.masjidku.events.client;
    requires org.masjidku.common;
    requires org.jetbrains.annotations;
    requires org.hibernate.orm.core;
    requires jakarta.persistence;
    requires java.naming;
    requires org.slf4j;
    requires dagger;
    requires javax.inject;

    exports org.masjidku.events.di;
    opens org.masjidku.events.domain.entity to org.hibernate.orm.core;

    provides org.masjidku.events.client.EventsClient with EventsClientImpl;
}
