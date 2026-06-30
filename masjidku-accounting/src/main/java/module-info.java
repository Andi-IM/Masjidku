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

    opens org.masjidku.accounting.domain.entity;

    provides org.masjidku.accounting.client.service.AccountingClient with org.masjidku.accounting.service.impl.AccountingClientImpl;
}

