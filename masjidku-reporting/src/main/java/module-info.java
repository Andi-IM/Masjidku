module org.masjidku.reporting.masjidkureporting {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;

    opens org.masjidku.reporting.masjidkureporting to javafx.fxml;
    exports org.masjidku.reporting.masjidkureporting;
}