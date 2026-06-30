module org.masjidku.accounting {
    requires org.masjidku.common;
    requires javafx.base;
    requires org.masjidku.accounting.client;
    requires org.jetbrains.annotations;
    requires org.hibernate.orm.core;
    requires jakarta.persistence;
    requires java.naming;
    requires org.slf4j;
    requires java.sql;
    requires dagger;
    requires javax.inject;

    opens org.masjidku.accounting.domain.entity;
    exports org.masjidku.accounting.di;

    provides org.masjidku.accounting.client.service.AccountingClient with org.masjidku.accounting.service.impl.AccountingClientImpl;
}

