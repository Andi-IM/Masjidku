module org.masjidku.accounting.client {
    requires transitive javafx.base;
    requires transitive java.sql;

    exports org.masjidku.accounting.client.model.anakyatim;
    exports org.masjidku.accounting.client.model.operasional;
    exports org.masjidku.accounting.client.model.pembangunan;
    exports org.masjidku.accounting.client.model.tpa;
    exports org.masjidku.accounting.client.model.zakat;
    
    exports org.masjidku.accounting.client.service;
    
    
}
