open module main {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.base;
    requires java.sql;
    requires org.masjidku.common;
    requires com.google.common;
    requires org.slf4j;

    requires org.masjidku.accounting.client;
    requires org.masjidku.events.client;

    exports org.masjidku.model;
    exports org.masjidku.model.user;
    
    
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
    uses org.masjidku.events.client.service.KegiatanService;
    uses org.masjidku.events.client.service.TamuService;
    uses org.masjidku.events.client.service.TamuKegiatanService;
}
