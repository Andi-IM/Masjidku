/*
 * Copyright (c) 2021. Creative Commons Legal Code
 *
 *                            CC0 1.0 Universal
 *
 *                                CREATIVE COMMONS CORPORATION IS NOT A LAW FIRM AND DOES NOT PROVIDE
 *                                LEGAL SERVICES. DISTRIBUTION OF THIS DOCUMENT DOES NOT CREATE AN
 *                                ATTORNEY-CLIENT RELATIONSHIP. CREATIVE COMMONS PROVIDES THIS
 *                                INFORMATION ON AN "AS-IS" BASIS. CREATIVE COMMONS MAKES NO WARRANTIES
 *                                REGARDING THE USE OF THIS DOCUMENT OR THE INFORMATION OR WORKS
 *                                PROVIDED HEREUNDER, AND DISCLAIMS LIABILITY FOR DAMAGES RESULTING FROM
 *                                THE USE OF THIS DOCUMENT OR THE INFORMATION OR WORKS PROVIDED
 *                                HEREUNDER.
 */

package org.masjidku.navigation;

import java.util.ResourceBundle;
import java.util.MissingResourceException;

public enum AppRoute {
    CONTENT("home.fxml", Constants.ROUTE_BERANDA),
    LOGIN("login.fxml", "route.login"),
    ABOUT("about.fxml", "route.tentang"),
    PROFILE("profile.fxml", "route.profil"),

    // Admin
    ADMIN_ROOT("admin/admin_root.fxml", null),
    ADMIN_HOME("admin/admin_home.fxml", Constants.ROUTE_BERANDA),
    USER_LIST("admin/user_lists.fxml", "route.kelola_user"),
    USER_LOG("admin/user_logs.fxml", "route.log_user"),
    
    // Principal
    PRINCIPAL_ROOT("principal/principal_root.fxml", null),
    PRINCIPAL_HOME("principal/principal_home.fxml", Constants.ROUTE_BERANDA),
    PRINCIPAL_LAPORAN("principal/principal_laporan.fxml", Constants.ROUTE_BACA_LAPORAN),
    PRINCIPAL_DATA("principal/principal_read_data.fxml", Constants.ROUTE_BACA_DATA),

    REPORT_KEGIATAN("principal/report/kegiatan/report_kegiatan.fxml", Constants.ROUTE_BACA_LAPORAN),
    LIST_KEGIATAN("principal/report/kegiatan/list_kegiatan.fxml", Constants.ROUTE_BACA_DATA),
    LIST_TAMU("principal/report/kegiatan/list_tamu.fxml", Constants.ROUTE_BACA_DATA),
    LIST_UNDANGAN("principal/report/kegiatan/list_undangan.fxml", Constants.ROUTE_BACA_DATA),

    REPORT_ANAKYATIM("principal/report/keuangan/report_anakyatim.fxml", Constants.ROUTE_BACA_LAPORAN),
    LIST_DONATUR_ANAKYATIM("principal/report/keuangan/anakyatim/list_donatur_anakyatim.fxml", Constants.ROUTE_BACA_DATA),
    LIST_ANAKYATIM("principal/report/keuangan/anakyatim/list_anakyatim.fxml", Constants.ROUTE_BACA_DATA),

    REPORT_PEMBANGUNAN("principal/report/keuangan/report_pembangunan.fxml", Constants.ROUTE_BACA_LAPORAN),
    LIST_DONATUR_PEMBANGUNAN("principal/report/keuangan/pembangunan/list_donatur_pembangunan.fxml", Constants.ROUTE_BACA_DATA),
    LIST_PEMBANGUNAN("principal/report/keuangan/pembangunan/list_pembangunan.fxml", Constants.ROUTE_BACA_DATA),

    REPORT_OPERASIONAL("principal/report/keuangan/report_operasional.fxml", Constants.ROUTE_BACA_LAPORAN),
    LIST_DONATUR_OPERASIONAL("principal/report/keuangan/operasional/list_donatur_operasional.fxml", Constants.ROUTE_BACA_DATA),
    LIST_OPERASIONAL("principal/report/keuangan/operasional/list_operasional.fxml", Constants.ROUTE_BACA_DATA),

    REPORT_ZAKAT("principal/report/keuangan/report_zakat.fxml", Constants.ROUTE_BACA_LAPORAN),
    LIST_DONATUR_ZAKAT("principal/report/keuangan/zakat/list_donatur_zakat.fxml", Constants.ROUTE_BACA_DATA),
    LIST_ZAKAT("principal/report/keuangan/zakat/list_zakat.fxml", Constants.ROUTE_BACA_DATA),

    REPORT_TPA("principal/report/keuangan/report_tpa.fxml", Constants.ROUTE_BACA_LAPORAN),
    LIST_DONATUR_TPA("principal/report/keuangan/tpa/list_donatur_tpa.fxml", Constants.ROUTE_BACA_DATA),
    LIST_TPA("principal/report/keuangan/tpa/list_tpa.fxml", Constants.ROUTE_BACA_DATA),

    // Secretary
    SECRETARY_ROOT("secretary/secretary_root.fxml", null),
    SECRETARY_HOME("secretary/secretary_home.fxml", Constants.ROUTE_BERANDA),
    SECRETARY_KEGIATAN("secretary/list_kegiatan.fxml", "route.kelola_kegiatan"),
    SECRETARY_TAMU("secretary/list_tamu.fxml", "route.kelola_tamu"),
    SECRETARY_UNDANGAN("secretary/list_undangan.fxml", "route.kelola_undangan"),

    // Accountant
    ACCOUNTANT_ROOT("accountant/accountant_root.fxml", null),
    ACCOUNTANT_HOME("accountant/accountant_home.fxml", Constants.ROUTE_BERANDA),
    
    ACCOUNTANT_ANAKYATIM("accountant/accountant_anakyatim.fxml", Constants.ROUTE_ANAK_YATIM),
    ACCOUNTANT_DONASI_ANAKYATIM("accountant/anakyatim/list_donatur_anakyatim.fxml", Constants.ROUTE_ANAK_YATIM),
    ACCOUNTANT_DAFTAR_ANAKYATIM("accountant/anakyatim/list_anakyatim.fxml", Constants.ROUTE_ANAK_YATIM),
    
    ACCOUNTANT_ZAKAT("accountant/accountant_zakat.fxml", Constants.ROUTE_ZAKAT),
    ACCOUNTANT_DONATUR_ZAKAT("accountant/zakat/list_donatur_zakat.fxml", Constants.ROUTE_ZAKAT),
    ACCOUNTANT_DAFTAR_PENERIMA_ZAKAT("accountant/zakat/list_zakat.fxml", Constants.ROUTE_ZAKAT),
    
    ACCOUNTANT_PEMBANGUNAN("accountant/accountant_pembangunan.fxml", Constants.ROUTE_PEMBANGUNAN),
    ACCOUNTANT_DONATUR_PEMBANGUNAN("accountant/pembangunan/list_donatur_pembangunan.fxml", Constants.ROUTE_PEMBANGUNAN),
    ACCOUNTANT_ALOKASI_PEMBANGUNAN("accountant/pembangunan/list_pembangunan.fxml", Constants.ROUTE_PEMBANGUNAN),
    
    ACCOUNTANT_OPERASIONAL("accountant/accountant_operasional.fxml", Constants.ROUTE_OPERASIONAL),
    ACCOUNTANT_DONATUR_OPERASIONAL("accountant/operasional/list_donatur_operasional.fxml", Constants.ROUTE_OPERASIONAL),
    ACCOUNTANT_ALOKASI_OPERASIONAL("accountant/operasional/list_operasional.fxml", Constants.ROUTE_OPERASIONAL),
    
    ACCOUNTANT_TPA("accountant/accountant_tpa.fxml", Constants.ROUTE_TPA),
    ACCOUNTANT_DONATUR_TPA("accountant/tpa/list_donatur_tpa.fxml", Constants.ROUTE_TPA),
    ACCOUNTANT_ALOKASI_TPA("accountant/tpa/list_tpa.fxml", Constants.ROUTE_TPA);

    private static class Constants {
        public static final String ROUTE_BERANDA = "route.beranda";
        public static final String ROUTE_BACA_LAPORAN = "route.baca_laporan";
        public static final String ROUTE_BACA_DATA = "route.baca_data";
        public static final String ROUTE_ANAK_YATIM = "route.anak_yatim";
        public static final String ROUTE_ZAKAT = "route.zakat";
        public static final String ROUTE_PEMBANGUNAN = "route.pembangunan";
        public static final String ROUTE_OPERASIONAL = "route.operasional";
        public static final String ROUTE_TPA = "route.tpa";
    }

    private static final ResourceBundle bundle;

    static {
        ResourceBundle temp = null;
        try {
            temp = ResourceBundle.getBundle("org.masjidku.messages");
        } catch (MissingResourceException e) {
            // Fallback will use keys directly
        }
        bundle = temp;
    }

    private final String fxmlPath;
    private final String sidebarKey;

    AppRoute(String fxmlPath, String sidebarKey) {
        this.fxmlPath = fxmlPath;
        this.sidebarKey = sidebarKey;
    }

    public String getFxmlPath() {
        return fxmlPath;
    }

    public String getSidebarText() {
        if (sidebarKey == null) {
            return null;
        }
        if (bundle != null && bundle.containsKey(sidebarKey)) {
            return bundle.getString(sidebarKey);
        }
        return sidebarKey;
    }
}
