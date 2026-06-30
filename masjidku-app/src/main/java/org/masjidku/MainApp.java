
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

package org.masjidku;

import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.stage.Stage;
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
import org.masjidku.model.session.dao.SessionManager;
import org.masjidku.model.user.User;
import org.masjidku.model.user.UserProfile;
import org.masjidku.navigation.AppRouter;
import org.masjidku.util.ViewManager;

public class MainApp extends Application implements AppRouter {

    private Stage primaryStage;
    private ViewManager viewManager;

    public void loadHomeRoot() {
        viewManager.loadHomeRoot();
    }

    @Override
    public void start(Stage primaryStage) {
        // Initialize Dagger 2 DI
        org.masjidku.di.DiProvider.init();

        // Initialize SQLite Database schema if necessary
        org.masjidku.util.db.SQLiteInitService.initializeDatabase();

        this.primaryStage = primaryStage;
        this.viewManager = new ViewManager(primaryStage, this);
        this.primaryStage.setTitle("Masjidku");
        this.primaryStage.resizableProperty().setValue(Boolean.FALSE);
        // App icon
        this.primaryStage.getIcons()
                .add(new Image("file:src/main/resources/org/masjidku/icon/favicon.png"));

        initRootLayout();
        showContent();
    }

    @Override
    public void stop() throws Exception {
        org.masjidku.di.DiProvider.getAppComponent().getSessionManager().logout();
        super.stop();
    }

    /**
     * Initializes the root layout
     */
    public void initRootLayout() {
        loadHomeRoot();
        if (primaryStage != null) {
            primaryStage.show();
        }
    }

    /**
     * Show the content inside the root layout
     */
    public void showContent() {
        viewManager.showContent();
    }

    /**
     * Show User Login
     */
    public void showLogin() {
        viewManager.showLogin();
    }

    /**
     * Show App About
     */
    public void showAbout() {
        viewManager.showAbout();
    }

    /**
     * record user sessions
     *
     * @param user user
     */
    public void recordSession(User user) {
        org.masjidku.di.DiProvider.getAppComponent().getSessionManager().login(user);
    }

    /**
     * show user's profile
     */
    public void showProfile() {
        viewManager.showProfile();
    }

    /**
     * User profile edit
     *
     * @param profile the user profile
     */
    public void editProfile(UserProfile profile) {
        viewManager.editProfile(profile);
    }


    /**
     * Admin Privilege
     */
    public void setAdminView() {
        viewManager.setAdminView();
    }

    /**
     * Admin Home
     */
    public void showAdminHome() {
        viewManager.showAdminHome();
    }

    /**
     * show list of user
     */
    public void showUser() {
        viewManager.showUser();
    }

    /**
     * Open the scene to edit detail for the specified user. If the user
     * clicks OK, the changes are saved to the database
     *
     * @param user the user object to be edited.
     */
    public void showUserEditScene(User user) {
        viewManager.showUserEditScene(user);
    }

    /**
     * Show list of User Activities
     */
    public void showUserLog() {
        viewManager.showUserLog();
    }

    /**
     * Principal Privilege
     */
    public void setPrincipalView() {
        viewManager.setPrincipalView();
    }

    /**
     * Showing principal home
     */
    public void showPrincipalHome() {
        viewManager.showPrincipalHome();
    }

    /**
     * Show laporan
     */
    public void showLaporan() {
        viewManager.showLaporan();
    }

    public void showKegiatanReport() {
        viewManager.showKegiatanData();
    }

    public void showAnakYatimReport() {
        viewManager.showAnakYatimData();
    }

    public void showPembangunanReport() {
        viewManager.showPembangunanData();
    }

    public void showOperasionalReport() {
        viewManager.showOperasionalData();
    }

    public void showZakatReport() {
        viewManager.showZakatData();
    }

    public void showTpaReport() {
        viewManager.showTpaData();
    }

    /**
     * Show data
     */
    public void showData() {
        viewManager.showData();
    }

    public void showKegiatanOverview() {
        viewManager.showKegiatanOverview();
    }

    public void showKegiatanData() {
        viewManager.showKegiatanData();
    }

    public void showTamuData() {
        viewManager.showTamuData();
    }

    public void showUndanganData() {
        viewManager.showUndanganData();
    }

    /**
     * Keuangan Data
     */
    // Anak Yatim
    public void showAnakYatimData() {
        viewManager.showAnakYatimData();
    }

    public void showAnakYatimMasuk() {
        viewManager.showAnakYatimMasuk();
    }

    public void showAnakYatimKeluar() {
        viewManager.showAnakYatimKeluar();
    }

    // Pembangunan
    public void showPembangunanData() {
        viewManager.showPembangunanData();
    }

    public void showPembangunanMasuk() {
        viewManager.showPembangunanMasuk();
    }

    public void showPembangunanKeluar() {
        viewManager.showPembangunanKeluar();
    }

    // Operasional
    public void showOperasionalData() {
        viewManager.showOperasionalData();
    }

    public void showOperasionalMasuk() {
        viewManager.showOperasionalMasuk();
    }

    public void showOperasionalKeluar() {
        viewManager.showOperasionalKeluar();
    }

    // Zakat
    public void showZakatData() {
        viewManager.showZakatData();
    }

    public void showZakatMasuk() {
        viewManager.showZakatMasuk();
    }

    public void showZakatKeluar() {
        viewManager.showZakatKeluar();
    }

    // TPA
    public void showTpaData() {
        viewManager.showTpaData();
    }

    public void showTpaMasuk() {
        viewManager.showTpaMasuk();
    }

    public void showTpaKeluar() {
        viewManager.showTpaKeluar();
    }

    /**
     * Secretary Privilege
     */
    public void setSecretaryView() {
        viewManager.setSecretaryView();
    }

    /**
     * Showing secretary home
     */
    public void setSecretaryHome() {
        viewManager.setSecretaryHome();
    }

    /**
     * Showing secretary kegiatan
     */
    public void showKegiatan() {
        viewManager.showKegiatan();
    }

    /**
     * Showing secretary edit kegiatan
     */
    public void showKegiatanEditform(Kegiatan kegiatan) {
        viewManager.showKegiatanEditform(kegiatan);
    }

    /**
     * Showing secretary tamu
     */
    public void showTamu() {
        viewManager.showTamu();
    }

    /**
     * Showing secretary tamu edit
     *
     * @param tamu tamu
     */
    public void showTamuEditForm(Tamu tamu) {
        viewManager.showTamuEditForm(tamu);
    }

    /**
     * showing undangan
     */
    public void showUndangan() {
        viewManager.showUndangan();
    }

    /**
     * Showing undangan edit form
     *
     * @param undangan undangan
     */
    public void showUndanganEditForm(TamuKegiatan undangan) {
        viewManager.showUndanganEditForm(undangan);
    }

    /**
     * Accountant Privilege
     */
    public void setAccountantView() {
        viewManager.setAccountantView();
    }

    /**
     * show accountant home
     */
    public void setAccountantHome() {
        viewManager.setAccountantHome();
    }

    /**
     * show anakYatimprompt
     */
    public void showAnakYatim() {
        viewManager.showAnakYatim();
    }

    public void showDonasiAYatim() {
        viewManager.showDonasiAYatim();
    }

    public void showDaftarAnakYatim() {
        viewManager.showDaftarAnakYatim();
    }

    public void editDonaturAnakYatim(DonasiAYatim model) {
        viewManager.editDonaturAnakYatim(model);
    }

    public void editAnakYatim(AnakYatim model) {
        viewManager.editAnakYatim(model);
    }

    /**
     * show zakatPrompt
     */
    public void showZakat() {
        viewManager.showZakat();
    }

    public void showDonaturZakat() {
        viewManager.showDonaturZakat();
    }

    public void showDaftarPenerimaZakat() {
        viewManager.showDaftarPenerimaZakat();
    }

    public void editDonaturZakat(ZakatMasuk model) {
        viewManager.editDonaturZakat(model);
    }

    public void editPenerimaZakat(ZakatKeluar model) {
        viewManager.editPenerimaZakat(model);
    }

    /**
     * show Pembangunan
     */
    public void showPembangunan() {
        viewManager.showPembangunan();
    }

    public void showDonaturPembangunan() {
        viewManager.showDonaturPembangunan();
    }

    public void showAlokasiPembangunan() {
        viewManager.showAlokasiPembangunan();
    }

    public void editDonaturPembangunan(DonasiPembangunan model) {
        viewManager.editDonaturPembangunan(model);
    }

    public void editAlokasiPembangunan(Pembangunan model) {
        viewManager.editAlokasiPembangunan(model);
    }

    /**
     * show operational
     */
    public void showOperasional() {
        viewManager.showOperasional();
    }

    public void showDonaturOperasional() {
        viewManager.showDonaturOperasional();
    }

    public void showAlokasiOperasional() {
        viewManager.showAlokasiOperasional();
    }

    public void editDonaturOperasional(DonasiOperasional model) {
        viewManager.editDonaturOperasional(model);
    }

    public void editAlokasiOperasional(Operasional model) {
        viewManager.editAlokasiOperasional(model);
    }

    /**
     * show tpa
     */
    public void showTpa() {
        viewManager.showTpa();
    }

    public void showDonaturTpa() {
        viewManager.showDonaturTpa();
    }

    public void showAlokasiTpa() {
        viewManager.showAlokasiTpa();
    }

    public void editDonaturTpa(TpaMasuk model) {
        viewManager.editDonaturTpa(model);
    }

    public void editAlokasiTpa(TpaKeluar model) {
        viewManager.editAlokasiTpa(model);
    }

    /**
     * Logout
     */
    public void onLogoutAction() {
        org.masjidku.di.DiProvider.getAppComponent().getSessionManager().logout();

        loadHomeRoot();
        showContent();
    }

    /**
     * Returns the main stage
     *
     * @param args arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
}

