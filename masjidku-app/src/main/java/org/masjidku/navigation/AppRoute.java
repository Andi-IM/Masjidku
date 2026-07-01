package org.masjidku.navigation;

public enum AppRoute {
    CONTENT("home.fxml", Labels.BERANDA),
    LOGIN("login.fxml", "Login"),
    ABOUT("about.fxml", "Tentang"),
    PROFILE("profile.fxml", "Profil"),

    // Admin
    ADMIN_ROOT("admin/admin_root.fxml", null),
    ADMIN_HOME("admin/admin_home.fxml", Labels.BERANDA),
    USER_LIST("admin/user_lists.fxml", "Kelola User"),
    USER_LOG("admin/user_logs.fxml", "Log User"),
    
    // Principal
    PRINCIPAL_ROOT("principal/principal_root.fxml", null),
    PRINCIPAL_HOME("principal/principal_home.fxml", Labels.BERANDA),
    PRINCIPAL_LAPORAN("principal/principal_laporan.fxml", Labels.BACA_LAPORAN),
    PRINCIPAL_DATA("principal/principal_read_data.fxml", Labels.BACA_DATA),

    REPORT_KEGIATAN("principal/report/kegiatan/report_kegiatan.fxml", Labels.BACA_LAPORAN),
    LIST_KEGIATAN("principal/report/kegiatan/list_kegiatan.fxml", Labels.BACA_DATA),
    LIST_TAMU("principal/report/kegiatan/list_tamu.fxml", Labels.BACA_DATA),
    LIST_UNDANGAN("principal/report/kegiatan/list_undangan.fxml", Labels.BACA_DATA),

    REPORT_ANAKYATIM("principal/report/keuangan/report_anakyatim.fxml", Labels.BACA_LAPORAN),
    LIST_DONATUR_ANAKYATIM("principal/report/keuangan/anakyatim/list_donatur_anakyatim.fxml", Labels.BACA_DATA),
    LIST_ANAKYATIM("principal/report/keuangan/anakyatim/list_anakyatim.fxml", Labels.BACA_DATA),

    REPORT_PEMBANGUNAN("principal/report/keuangan/report_pembangunan.fxml", Labels.BACA_LAPORAN),
    LIST_DONATUR_PEMBANGUNAN("principal/report/keuangan/pembangunan/list_donatur_pembangunan.fxml", Labels.BACA_DATA),
    LIST_PEMBANGUNAN("principal/report/keuangan/pembangunan/list_pembangunan.fxml", Labels.BACA_DATA),

    REPORT_OPERASIONAL("principal/report/keuangan/report_operasional.fxml", Labels.BACA_LAPORAN),
    LIST_DONATUR_OPERASIONAL("principal/report/keuangan/operasional/list_donatur_operasional.fxml", Labels.BACA_DATA),
    LIST_OPERASIONAL("principal/report/keuangan/operasional/list_operasional.fxml", Labels.BACA_DATA),

    REPORT_ZAKAT("principal/report/keuangan/report_zakat.fxml", Labels.BACA_LAPORAN),
    LIST_DONATUR_ZAKAT("principal/report/keuangan/zakat/list_donatur_zakat.fxml", Labels.BACA_DATA),
    LIST_ZAKAT("principal/report/keuangan/zakat/list_zakat.fxml", Labels.BACA_DATA),

    REPORT_TPA("principal/report/keuangan/report_tpa.fxml", Labels.BACA_LAPORAN),
    LIST_DONATUR_TPA("principal/report/keuangan/tpa/list_donatur_tpa.fxml", Labels.BACA_DATA),
    LIST_TPA("principal/report/keuangan/tpa/list_tpa.fxml", Labels.BACA_DATA),

    // Secretary
    SECRETARY_ROOT("secretary/secretary_root.fxml", null),
    SECRETARY_HOME("secretary/secretary_home.fxml", Labels.BERANDA),
    SECRETARY_KEGIATAN("secretary/list_kegiatan.fxml", "Kelola Kegiatan"),
    SECRETARY_TAMU("secretary/list_tamu.fxml", "Kelola Tamu"),
    SECRETARY_UNDANGAN("secretary/list_undangan.fxml", "Kelola Undangan"),

    // Accountant
    ACCOUNTANT_ROOT("accountant/accountant_root.fxml", null),
    ACCOUNTANT_HOME("accountant/accountant_home.fxml", Labels.BERANDA),
    
    ACCOUNTANT_ANAKYATIM("accountant/accountant_anakyatim.fxml", Labels.ANAK_YATIM),
    ACCOUNTANT_DONASI_ANAKYATIM("accountant/anakyatim/list_donatur_anakyatim.fxml", Labels.ANAK_YATIM),
    ACCOUNTANT_DAFTAR_ANAKYATIM("accountant/anakyatim/list_anakyatim.fxml", Labels.ANAK_YATIM),
    
    ACCOUNTANT_ZAKAT("accountant/accountant_zakat.fxml", Labels.ZAKAT),
    ACCOUNTANT_DONATUR_ZAKAT("accountant/zakat/list_donatur_zakat.fxml", Labels.ZAKAT),
    ACCOUNTANT_DAFTAR_PENERIMA_ZAKAT("accountant/zakat/list_zakat.fxml", Labels.ZAKAT),
    
    ACCOUNTANT_PEMBANGUNAN("accountant/accountant_pembangunan.fxml", Labels.PEMBANGUNAN),
    ACCOUNTANT_DONATUR_PEMBANGUNAN("accountant/pembangunan/list_donatur_pembangunan.fxml", Labels.PEMBANGUNAN),
    ACCOUNTANT_ALOKASI_PEMBANGUNAN("accountant/pembangunan/list_pembangunan.fxml", Labels.PEMBANGUNAN),
    
    ACCOUNTANT_OPERASIONAL("accountant/accountant_operasional.fxml", Labels.OPERASIONAL),
    ACCOUNTANT_DONATUR_OPERASIONAL("accountant/operasional/list_donatur_operasional.fxml", Labels.OPERASIONAL),
    ACCOUNTANT_ALOKASI_OPERASIONAL("accountant/operasional/list_operasional.fxml", Labels.OPERASIONAL),
    
    ACCOUNTANT_TPA("accountant/accountant_tpa.fxml", Labels.TPA),
    ACCOUNTANT_DONATUR_TPA("accountant/tpa/list_donatur_tpa.fxml", Labels.TPA),
    ACCOUNTANT_ALOKASI_TPA("accountant/tpa/list_tpa.fxml", Labels.TPA);

    private static class Labels {
        static final String BERANDA = "Beranda";
        static final String BACA_LAPORAN = "Baca Laporan";
        static final String BACA_DATA = "Baca Data";
        static final String ANAK_YATIM = "Anak Yatim";
        static final String ZAKAT = "Zakat";
        static final String PEMBANGUNAN = "Pembangunan";
        static final String OPERASIONAL = "Operasional";
        static final String TPA = "TPA";
    }

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
