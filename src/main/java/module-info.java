open module main {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.base;
    requires java.sql;
    requires java.desktop;
    requires java.xml.bind;

    requires mysql.connector.java;
    requires com.google.common;

    requires org.masjidku.accounting.client;

    exports org.masjidku.model;
    exports org.masjidku.model.user;
    
    exports org.masjidku.util.db;
    exports org.masjidku.util.date;
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
}
