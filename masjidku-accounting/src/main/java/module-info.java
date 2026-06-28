module org.masjidku.accounting {
    requires java.sql;
    requires org.masjidku.common;
    requires javafx.base;
    requires org.masjidku.accounting.client;
    requires org.jetbrains.annotations;


    provides org.masjidku.accounting.client.service.AnakYatimService with org.masjidku.accounting.service.impl.AnakYatimServiceImpl;
    provides org.masjidku.accounting.client.service.DonasiAYatimService with org.masjidku.accounting.service.impl.DonasiAYatimServiceImpl;
    provides org.masjidku.accounting.client.service.DonasiOperationalService with org.masjidku.accounting.service.impl.DonasiOperationalServiceImpl;
    provides org.masjidku.accounting.client.service.OperationalService with org.masjidku.accounting.service.impl.OperationalServiceImpl;
    provides org.masjidku.accounting.client.service.DonasiPembangunanService with org.masjidku.accounting.service.impl.DonasiPembangunanServiceImpl;
    provides org.masjidku.accounting.client.service.PembangunanService with org.masjidku.accounting.service.impl.PembangunanServiceImpl;
    provides org.masjidku.accounting.client.service.TpaKeluarService with org.masjidku.accounting.service.impl.TpaKeluarServiceImpl;
    provides org.masjidku.accounting.client.service.TpaMasukService with org.masjidku.accounting.service.impl.TpaMasukServiceImpl;
    provides org.masjidku.accounting.client.service.ZakatKeluarService with org.masjidku.accounting.service.impl.ZakatKeluarServiceImpl;
    provides org.masjidku.accounting.client.service.ZakatMasukService with org.masjidku.accounting.service.impl.ZakatMasukServiceImpl;
    provides org.masjidku.accounting.client.service.AccountingFunctionsService with org.masjidku.accounting.service.impl.AccountingFunctionsServiceImpl;
}
