module org.masjidku.common {
    requires transitive java.sql;
    requires java.logging;
    
    // Optional module dependencies for database drivers
    requires static mysql.connector.java;
    requires static org.xerial.sqlitejdbc;
    
    exports org.masjidku.util.db;
}


