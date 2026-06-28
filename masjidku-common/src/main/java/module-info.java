module org.masjidku.common {
    requires java.sql;
    requires java.logging;
    requires mysql.connector.java;
    exports org.masjidku.util.db;
}
