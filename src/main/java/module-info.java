open module main {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.base;
    requires java.sql;
    requires java.desktop;
    requires java.xml.bind;
    requires jasperreports;
    requires mysql.connector.java;
    requires com.google.common;
    requires com.github.librepdf.openpdf;

    exports org.masjidku.model.user;
    exports org.masjidku.model.accounting.operasional;
    exports org.masjidku.util.db;
    exports org.masjidku.util.date;
}