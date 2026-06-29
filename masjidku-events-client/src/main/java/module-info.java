module org.masjidku.events.client {
    requires transitive javafx.base;
    requires transitive java.sql; // for SQLException in some legacy model methods if any, but better not needed

    exports org.masjidku.events.client.model;
    exports org.masjidku.events.client.service;
    exports org.masjidku.events.client.repository;
}

