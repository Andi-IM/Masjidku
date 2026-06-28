module org.masjidku.events.masjidkuevents {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;

    opens org.masjidku.events.masjidkuevents to javafx.fxml;
    exports org.masjidku.events.masjidkuevents;
}