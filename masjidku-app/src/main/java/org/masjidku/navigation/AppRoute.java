package org.masjidku.navigation;

public enum AppRoute {
    CONTENT("home.fxml", "Beranda"),
    LOGIN("login.fxml", "Login"),
    ABOUT("about.fxml", "Tentang"),
    PROFILE("profile.fxml", "Profil"),

    // Admin
    ADMIN_ROOT("admin/admin_root.fxml", null),
    ADMIN_HOME("admin/admin_home.fxml", "Beranda"),
    USER_LIST("admin/user_lists.fxml", "Kelola User"),
    USER_LOG("admin/user_logs.fxml", "Log User"),
    
    // Principal
    PRINCIPAL_ROOT("principal/principal_root.fxml", null),
    PRINCIPAL_HOME("principal/principal_home.fxml", "Beranda"),
    PRINCIPAL_LAPORAN("principal/principal_laporan.fxml", "Baca Laporan"),
    PRINCIPAL_DATA("principal/principal_read_data.fxml", "Baca Data"),

    REPORT_KEGIATAN("principal/report/kegiatan/report_kegiatan.fxml", "Baca Laporan"),
    LIST_KEGIATAN("principal/report/kegiatan/list_kegiatan.fxml", "Baca Data"),
    LIST_TAMU("principal/report/kegiatan/list_tamu.fxml", "Baca Data"),
    LIST_UNDANGAN("principal/report/kegiatan/list_undangan.fxml", "Baca Data"),

    REPORT_ANAKYATIM("principal/report/keuangan/report_anakyatim.fxml", "Baca Laporan"),
    LIST_DONATUR_ANAKYATIM("principal/report/keuangan/anakyatim/list_donatur_anakyatim.fxml", "Baca Data"),
    LIST_ANAKYATIM("principal/report/keuangan/anakyatim/list_anakyatim.fxml", "Baca Data"),

    REPORT_PEMBANGUNAN("principal/report/keuangan/report_pembangunan.fxml", "Baca Laporan"),
    LIST_DONATUR_PEMBANGUNAN("principal/report/keuangan/pembangunan/list_donatur_pembangunan.fxml", "Baca Data"),
    LIST_PEMBANGUNAN("principal/report/keuangan/pembangunan/list_pembangunan.fxml", "Baca Data"),

    REPORT_OPERASIONAL("principal/report/keuangan/report_operasional.fxml", "Baca Laporan"),
    LIST_DONATUR_OPERASIONAL("principal/report/keuangan/operasional/list_donatur_operasional.fxml", "Baca Data"),
    LIST_OPERASIONAL("principal/report/keuangan/operasional/list_operasional.fxml", "Baca Data"),

    REPORT_ZAKAT("principal/report/keuangan/report_zakat.fxml", "Baca Laporan"),
    LIST_DONATUR_ZAKAT("principal/report/keuangan/zakat/list_donatur_zakat.fxml", "Baca Data"),
    LIST_ZAKAT("principal/report/keuangan/zakat/list_zakat.fxml", "Baca Data"),

    REPORT_TPA("principal/report/keuangan/report_tpa.fxml", "Baca Laporan"),
    LIST_DONATUR_TPA("principal/report/keuangan/tpa/list_donatur_tpa.fxml", "Baca Data"),
    LIST_TPA("principal/report/keuangan/tpa/list_tpa.fxml", "Baca Data"),

    // Secretary
    SECRETARY_ROOT("secretary/secretary_root.fxml", null),
    SECRETARY_HOME("secretary/secretary_home.fxml", "Beranda"),
    SECRETARY_KEGIATAN("secretary/list_kegiatan.fxml", "Kelola Kegiatan"),
    SECRETARY_TAMU("secretary/list_tamu.fxml", "Kelola Tamu"),
    SECRETARY_UNDANGAN("secretary/list_undangan.fxml", "Kelola Undangan"),

    // Accountant
    ACCOUNTANT_ROOT("accountant/accountant_root.fxml", null),
    ACCOUNTANT_HOME("accountant/accountant_home.fxml", "Beranda"),
    
    ACCOUNTANT_ANAKYATIM("accountant/accountant_anakyatim.fxml", "Anak Yatim"),
    ACCOUNTANT_DONASI_ANAKYATIM("accountant/anakyatim/list_donatur_anakyatim.fxml", "Anak Yatim"),
    ACCOUNTANT_DAFTAR_ANAKYATIM("accountant/anakyatim/list_anakyatim.fxml", "Anak Yatim"),
    
    ACCOUNTANT_ZAKAT("accountant/accountant_zakat.fxml", "Zakat"),
    ACCOUNTANT_DONATUR_ZAKAT("accountant/zakat/list_donatur_zakat.fxml", "Zakat"),
    ACCOUNTANT_DAFTAR_PENERIMA_ZAKAT("accountant/zakat/list_zakat.fxml", "Zakat"),
    
    ACCOUNTANT_PEMBANGUNAN("accountant/accountant_pembangunan.fxml", "Pembangunan"),
    ACCOUNTANT_DONATUR_PEMBANGUNAN("accountant/pembangunan/list_donatur_pembangunan.fxml", "Pembangunan"),
    ACCOUNTANT_ALOKASI_PEMBANGUNAN("accountant/pembangunan/list_pembangunan.fxml", "Pembangunan"),
    
    ACCOUNTANT_OPERASIONAL("accountant/accountant_operasional.fxml", "Operasional"),
    ACCOUNTANT_DONATUR_OPERASIONAL("accountant/operasional/list_donatur_operasional.fxml", "Operasional"),
    ACCOUNTANT_ALOKASI_OPERASIONAL("accountant/operasional/list_operasional.fxml", "Operasional"),
    
    ACCOUNTANT_TPA("accountant/accountant_tpa.fxml", "TPA"),
    ACCOUNTANT_DONATUR_TPA("accountant/tpa/list_donatur_tpa.fxml", "TPA"),
    ACCOUNTANT_ALOKASI_TPA("accountant/tpa/list_tpa.fxml", "TPA");

    private final String fxmlPath;
    private final String sidebarText;

    AppRoute(String fxmlPath, String sidebarText) {
        this.fxmlPath = fxmlPath;
        this.sidebarText = sidebarText;
    }

    public String getFxmlPath() {
        return fxmlPath;
    }

    public String getSidebarText() {
        return sidebarText;
    }
}
