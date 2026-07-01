open module main {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.base;
    requires com.google.common;
    requires org.slf4j;
    requires net.synedra.validatorfx;

    requires org.masjidku.accounting.client;
    requires org.masjidku.events.client;
    requires org.masjidku.reporting.client;
    requires org.masjidku.auth.client;
    requires org.masjidku.auth;
    requires org.masjidku.accounting;
    requires org.masjidku.events;
    requires org.masjidku.common;
    requires dagger;
    requires javax.inject;
    requires org.hibernate.orm.core;

    uses org.masjidku.accounting.client.service.AccountingClient;
    uses org.masjidku.events.client.EventsClient;
    uses org.masjidku.events.client.model.Kegiatan;
    uses org.masjidku.reporting.client.service.ReportService;
    uses org.masjidku.auth.client.AuthClient;
}


