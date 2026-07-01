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
    CONTENT("home.fxml", "route.beranda"),
    LOGIN("login.fxml", "route.login"),
    ABOUT("about.fxml", "route.tentang"),
    PROFILE("profile.fxml", "route.profil"),

    // Admin
    ADMIN_ROOT("admin/admin_root.fxml", null),
    ADMIN_HOME("admin/admin_home.fxml", "route.beranda"),
    USER_LIST("admin/user_lists.fxml", "route.kelola_user"),
    USER_LOG("admin/user_logs.fxml", "route.log_user"),
    
    // Principal
    PRINCIPAL_ROOT("principal/principal_root.fxml", null),
    PRINCIPAL_HOME("principal/principal_home.fxml", "route.beranda"),
    PRINCIPAL_LAPORAN("principal/principal_laporan.fxml", "route.baca_laporan"),
    PRINCIPAL_DATA("principal/principal_read_data.fxml", "route.baca_data"),

    REPORT_KEGIATAN("principal/report/kegiatan/report_kegiatan.fxml", "route.baca_laporan"),
    LIST_KEGIATAN("principal/report/kegiatan/list_kegiatan.fxml", "route.baca_data"),
    LIST_TAMU("principal/report/kegiatan/list_tamu.fxml", "route.baca_data"),
    LIST_UNDANGAN("principal/report/kegiatan/list_undangan.fxml", "route.baca_data"),

    REPORT_ANAKYATIM("principal/report/keuangan/report_anakyatim.fxml", "route.baca_laporan"),
    LIST_DONATUR_ANAKYATIM("principal/report/keuangan/anakyatim/list_donatur_anakyatim.fxml", "route.baca_data"),
    LIST_ANAKYATIM("principal/report/keuangan/anakyatim/list_anakyatim.fxml", "route.baca_data"),

    REPORT_PEMBANGUNAN("principal/report/keuangan/report_pembangunan.fxml", "route.baca_laporan"),
    LIST_DONATUR_PEMBANGUNAN("principal/report/keuangan/pembangunan/list_donatur_pembangunan.fxml", "route.baca_data"),
    LIST_PEMBANGUNAN("principal/report/keuangan/pembangunan/list_pembangunan.fxml", "route.baca_data"),

    REPORT_OPERASIONAL("principal/report/keuangan/report_operasional.fxml", "route.baca_laporan"),
    LIST_DONATUR_OPERASIONAL("principal/report/keuangan/operasional/list_donatur_operasional.fxml", "route.baca_data"),
    LIST_OPERASIONAL("principal/report/keuangan/operasional/list_operasional.fxml", "route.baca_data"),

    REPORT_ZAKAT("principal/report/keuangan/report_zakat.fxml", "route.baca_laporan"),
    LIST_DONATUR_ZAKAT("principal/report/keuangan/zakat/list_donatur_zakat.fxml", "route.baca_data"),
    LIST_ZAKAT("principal/report/keuangan/zakat/list_zakat.fxml", "route.baca_data"),

    REPORT_TPA("principal/report/keuangan/report_tpa.fxml", "route.baca_laporan"),
    LIST_DONATUR_TPA("principal/report/keuangan/tpa/list_donatur_tpa.fxml", "route.baca_data"),
    LIST_TPA("principal/report/keuangan/tpa/list_tpa.fxml", "route.baca_data"),

    // Secretary
    SECRETARY_ROOT("secretary/secretary_root.fxml", null),
    SECRETARY_HOME("secretary/secretary_home.fxml", "route.beranda"),
    SECRETARY_KEGIATAN("secretary/list_kegiatan.fxml", "route.kelola_kegiatan"),
    SECRETARY_TAMU("secretary/list_tamu.fxml", "route.kelola_tamu"),
    SECRETARY_UNDANGAN("secretary/list_undangan.fxml", "route.kelola_undangan"),

    // Accountant
    ACCOUNTANT_ROOT("accountant/accountant_root.fxml", null),
    ACCOUNTANT_HOME("accountant/accountant_home.fxml", "route.beranda"),
    
    ACCOUNTANT_ANAKYATIM("accountant/accountant_anakyatim.fxml", "route.anak_yatim"),
    ACCOUNTANT_DONASI_ANAKYATIM("accountant/anakyatim/list_donatur_anakyatim.fxml", "route.anak_yatim"),
    ACCOUNTANT_DAFTAR_ANAKYATIM("accountant/anakyatim/list_anakyatim.fxml", "route.anak_yatim"),
    
    ACCOUNTANT_ZAKAT("accountant/accountant_zakat.fxml", "route.zakat"),
    ACCOUNTANT_DONATUR_ZAKAT("accountant/zakat/list_donatur_zakat.fxml", "route.zakat"),
    ACCOUNTANT_DAFTAR_PENERIMA_ZAKAT("accountant/zakat/list_zakat.fxml", "route.zakat"),
    
    ACCOUNTANT_PEMBANGUNAN("accountant/accountant_pembangunan.fxml", "route.pembangunan"),
    ACCOUNTANT_DONATUR_PEMBANGUNAN("accountant/pembangunan/list_donatur_pembangunan.fxml", "route.pembangunan"),
    ACCOUNTANT_ALOKASI_PEMBANGUNAN("accountant/pembangunan/list_pembangunan.fxml", "route.pembangunan"),
    
    ACCOUNTANT_OPERASIONAL("accountant/accountant_operasional.fxml", "route.operasional"),
    ACCOUNTANT_DONATUR_OPERASIONAL("accountant/operasional/list_donatur_operasional.fxml", "route.operasional"),
    ACCOUNTANT_ALOKASI_OPERASIONAL("accountant/operasional/list_operasional.fxml", "route.operasional"),
    
    ACCOUNTANT_TPA("accountant/accountant_tpa.fxml", "route.tpa"),
    ACCOUNTANT_DONATUR_TPA("accountant/tpa/list_donatur_tpa.fxml", "route.tpa"),
    ACCOUNTANT_ALOKASI_TPA("accountant/tpa/list_tpa.fxml", "route.tpa");

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
