module org.masjidku.common {
    // Hibernate & JPA
    requires jakarta.persistence;
    requires org.hibernate.orm.core;
    requires dagger;
    requires javax.inject;
    requires java.naming;

    exports org.masjidku.common;
}