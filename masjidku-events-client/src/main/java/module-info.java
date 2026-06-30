module org.masjidku.events.client {
    requires transitive java.sql;
    requires transitive jakarta.persistence;
    exports org.masjidku.events.client;
    exports org.masjidku.events.client.model;

    opens org.masjidku.events.client.model;
    opens org.masjidku.events.client;
}
