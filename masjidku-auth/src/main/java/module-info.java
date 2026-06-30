module org.masjidku.auth {
    requires transitive java.sql;
    requires java.logging;
    requires org.slf4j;
    
    // Hibernate & JPA
    requires jakarta.persistence;
    requires org.hibernate.orm.core;
    requires com.zaxxer.hikari;
    requires com.google.common;
    requires dagger;
    requires javax.inject;
    
    // Optional module dependencies for database drivers
    requires static mysql.connector.java;
    requires static org.xerial.sqlitejdbc;
    requires org.masjidku.common; requires org.masjidku.auth.client;


    exports org.masjidku.auth.domain.repository;
    exports org.masjidku.auth.domain.repository.impl;
    exports org.masjidku.auth.domain.repository.base;
        
    opens org.masjidku.auth.domain.entity to org.hibernate.orm.core;
}


