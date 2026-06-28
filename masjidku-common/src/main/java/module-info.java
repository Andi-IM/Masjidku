module org.masjidku.common {
    requires transitive java.sql;
    requires java.logging;
    requires org.xerial.sqlitejdbc;
    requires mysql.connector.java;
    exports org.masjidku.util.db;
}

