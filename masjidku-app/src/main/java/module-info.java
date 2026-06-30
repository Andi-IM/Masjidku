open module main {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.base;
    requires org.masjidku.common;
    requires com.google.common;
    requires org.slf4j;
    requires net.synedra.validatorfx;

    requires org.masjidku.accounting.client;
    requires org.masjidku.events.client;
    requires org.masjidku.reporting.client;
    requires dagger;
    requires javax.inject;

    exports org.masjidku.model;

    uses org.masjidku.accounting.client.service.AnakYatimService;
    uses org.masjidku.accounting.client.service.DonasiAYatimService;
    uses org.masjidku.accounting.client.service.DonasiOperationalService;
    uses org.masjidku.accounting.client.service.OperationalService;
    uses org.masjidku.accounting.client.service.DonasiPembangunanService;
    uses org.masjidku.accounting.client.service.PembangunanService;
    uses org.masjidku.accounting.client.service.TpaKeluarService;
    uses org.masjidku.accounting.client.service.TpaMasukService;
    uses org.masjidku.accounting.client.service.ZakatKeluarService;
    uses org.masjidku.accounting.client.service.ZakatMasukService;
    uses org.masjidku.accounting.client.service.AccountingFunctionsService;
    uses org.masjidku.events.client.EventsClient;
    uses org.masjidku.events.client.model.Kegiatan;
    uses org.masjidku.reporting.client.service.ReportService;
}


