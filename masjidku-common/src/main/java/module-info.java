module org.masjidku.common {
    requires transitive java.sql;
    requires java.logging;
    requires org.slf4j;
    
    // Hibernate & JPA
    requires jakarta.persistence;
    requires org.hibernate.orm.core;
    requires com.zaxxer.hikari;
    requires com.google.common;
    
    // Optional module dependencies for database drivers
    requires static mysql.connector.java;
    requires static org.xerial.sqlitejdbc;
    
    exports org.masjidku.util.db;
    exports org.masjidku.model.user;
    exports org.masjidku.model.session;
    
    exports org.masjidku.domain.repository;
    exports org.masjidku.domain.repository.impl;
    
    opens org.masjidku.domain.entity to org.hibernate.orm.core;
}


