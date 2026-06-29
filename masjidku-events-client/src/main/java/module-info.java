module org.masjidku.events.client {
    requires transitive javafx.base;
    requires transitive java.sql;
    requires transitive jakarta.persistence;
    exports org.masjidku.events.client;
    exports org.masjidku.events.client.model;

    uses org.masjidku.events.domain.repository;
    opens org.masjidku.events.client.model to org.hibernate.orm.core;
    opens org.masjidku.events.client to org.hibernate.orm.core;
}
