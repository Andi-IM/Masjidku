module Masjidku.test {
    requires org.hamcrest;
    requires junit;
    requires Masjidku.main;

    opens org.masjidku.model to org.hamcrest, junit;
    opens org.masjidku.util to org.hamcrest, junit;
}