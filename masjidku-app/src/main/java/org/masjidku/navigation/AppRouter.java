package org.masjidku.navigation;

import org.masjidku.accounting.client.model.anakyatim.AnakYatim;
import org.masjidku.accounting.client.model.anakyatim.DonasiAYatim;
import org.masjidku.accounting.client.model.operasional.DonasiOperasional;
import org.masjidku.accounting.client.model.operasional.Operasional;
import org.masjidku.accounting.client.model.pembangunan.DonasiPembangunan;
import org.masjidku.accounting.client.model.pembangunan.Pembangunan;
import org.masjidku.accounting.client.model.tpa.TpaKeluar;
import org.masjidku.accounting.client.model.tpa.TpaMasuk;
import org.masjidku.accounting.client.model.zakat.ZakatKeluar;
import org.masjidku.accounting.client.model.zakat.ZakatMasuk;
import org.masjidku.events.client.model.Kegiatan;
import org.masjidku.events.client.model.Tamu;
import org.masjidku.events.client.model.TamuKegiatan;
import org.masjidku.model.user.User;
import org.masjidku.model.user.UserProfile;

public interface AppRouter {
    void navigate(AppRoute route);
    void navigateRoot(AppRoute route);
    default void showContent() { navigate(AppRoute.CONTENT); }
    default void showLogin() { navigate(AppRoute.LOGIN); }
    default void showAbout() { navigate(AppRoute.ABOUT); }
    void recordSession(User user);
    void showProfile();
    void editProfile(UserProfile profile);
    default void setAdminView() { navigateRoot(AppRoute.ADMIN_ROOT); }
    default void showAdminHome() { navigate(AppRoute.ADMIN_HOME); }
    default void showUser() { navigate(AppRoute.USER_LIST); }
    void showUserEditScene(User user);
    default void showUserLog() { navigate(AppRoute.USER_LOG); }
    default void setPrincipalView() { navigateRoot(AppRoute.PRINCIPAL_ROOT); }
    default void showPrincipalHome() { navigate(AppRoute.PRINCIPAL_HOME); }
    default void showLaporan() { navigate(AppRoute.PRINCIPAL_LAPORAN); }
    void showKegiatanReport();
    void showAnakYatimReport();
    void showPembangunanReport();
    void showOperasionalReport();
    void showZakatReport();
    void showTpaReport();
    default void showData() { navigate(AppRoute.PRINCIPAL_DATA); }
    default void showKegiatanOverview() { navigate(AppRoute.REPORT_KEGIATAN); }
    default void showKegiatanData() { navigate(AppRoute.LIST_KEGIATAN); }
    default void showTamuData() { navigate(AppRoute.LIST_TAMU); }
    default void showUndanganData() { navigate(AppRoute.LIST_UNDANGAN); }
    default void showAnakYatimData() { navigate(AppRoute.REPORT_ANAKYATIM); }
    default void showAnakYatimMasuk() { navigate(AppRoute.LIST_DONATUR_ANAKYATIM); }
    default void showAnakYatimKeluar() { navigate(AppRoute.LIST_ANAKYATIM); }
    default void showPembangunanData() { navigate(AppRoute.REPORT_PEMBANGUNAN); }
    default void showPembangunanMasuk() { navigate(AppRoute.LIST_DONATUR_PEMBANGUNAN); }
    default void showPembangunanKeluar() { navigate(AppRoute.LIST_PEMBANGUNAN); }
    default void showOperasionalData() { navigate(AppRoute.REPORT_OPERASIONAL); }
    default void showOperasionalMasuk() { navigate(AppRoute.LIST_DONATUR_OPERASIONAL); }
    default void showOperasionalKeluar() { navigate(AppRoute.LIST_OPERASIONAL); }
    default void showZakatData() { navigate(AppRoute.REPORT_ZAKAT); }
    default void showZakatMasuk() { navigate(AppRoute.LIST_DONATUR_ZAKAT); }
    default void showZakatKeluar() { navigate(AppRoute.LIST_ZAKAT); }
    default void showTpaData() { navigate(AppRoute.REPORT_TPA); }
    default void showTpaMasuk() { navigate(AppRoute.LIST_DONATUR_TPA); }
    default void showTpaKeluar() { navigate(AppRoute.LIST_TPA); }
    default void setSecretaryView() { navigateRoot(AppRoute.SECRETARY_ROOT); }
    default void setSecretaryHome() { navigate(AppRoute.SECRETARY_HOME); }
    default void showKegiatan() { navigate(AppRoute.SECRETARY_KEGIATAN); }
    void showKegiatanEditform(Kegiatan kegiatan);
    default void showTamu() { navigate(AppRoute.SECRETARY_TAMU); }
    void showTamuEditForm(Tamu tamu);
    default void showUndangan() { navigate(AppRoute.SECRETARY_UNDANGAN); }
    void showUndanganEditForm(TamuKegiatan undangan);
    default void setAccountantView() { navigateRoot(AppRoute.ACCOUNTANT_ROOT); }
    default void setAccountantHome() { navigate(AppRoute.ACCOUNTANT_HOME); }
    default void showAnakYatim() { navigate(AppRoute.ACCOUNTANT_ANAKYATIM); }
    default void showDonasiAYatim() { navigate(AppRoute.ACCOUNTANT_DONASI_ANAKYATIM); }
    default void showDaftarAnakYatim() { navigate(AppRoute.ACCOUNTANT_DAFTAR_ANAKYATIM); }
    void editDonaturAnakYatim(DonasiAYatim model);
    void editAnakYatim(AnakYatim model);
    default void showZakat() { navigate(AppRoute.ACCOUNTANT_ZAKAT); }
    default void showDonaturZakat() { navigate(AppRoute.ACCOUNTANT_DONATUR_ZAKAT); }
    default void showDaftarPenerimaZakat() { navigate(AppRoute.ACCOUNTANT_DAFTAR_PENERIMA_ZAKAT); }
    void editDonaturZakat(ZakatMasuk model);
    void editPenerimaZakat(ZakatKeluar model);
    default void showPembangunan() { navigate(AppRoute.ACCOUNTANT_PEMBANGUNAN); }
    default void showDonaturPembangunan() { navigate(AppRoute.ACCOUNTANT_DONATUR_PEMBANGUNAN); }
    default void showAlokasiPembangunan() { navigate(AppRoute.ACCOUNTANT_ALOKASI_PEMBANGUNAN); }
    void editDonaturPembangunan(DonasiPembangunan model);
    void editAlokasiPembangunan(Pembangunan model);
    default void showOperasional() { navigate(AppRoute.ACCOUNTANT_OPERASIONAL); }
    default void showDonaturOperasional() { navigate(AppRoute.ACCOUNTANT_DONATUR_OPERASIONAL); }
    default void showAlokasiOperasional() { navigate(AppRoute.ACCOUNTANT_ALOKASI_OPERASIONAL); }
    void editDonaturOperasional(DonasiOperasional model);
    void editAlokasiOperasional(Operasional model);
    default void showTpa() { navigate(AppRoute.ACCOUNTANT_TPA); }
    default void showDonaturTpa() { navigate(AppRoute.ACCOUNTANT_DONATUR_TPA); }
    default void showAlokasiTpa() { navigate(AppRoute.ACCOUNTANT_ALOKASI_TPA); }
    void editDonaturTpa(TpaMasuk model);
    void editAlokasiTpa(TpaKeluar model);
    void onLogoutAction();
}

