
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
import org.masjidku.di.DiProvider;
import org.masjidku.events.client.model.Kegiatan;
import org.masjidku.events.client.model.Tamu;
import org.masjidku.events.client.model.TamuKegiatan;
import org.masjidku.model.user.User;
import org.masjidku.model.user.UserProfile;
import org.masjidku.navigation.AppRoute;
import org.masjidku.navigation.AppRouter;
import org.masjidku.util.ViewManager;

import static org.masjidku.di.DiProvider.getAppComponent;
import static org.masjidku.util.db.SQLiteInitService.initializeDatabase;

public class MainApp extends Application implements AppRouter {

    private Stage primaryStage;
    private ViewManager viewManager;

    public void loadHomeRoot() {
        viewManager.loadHomeRoot();
    }

    @Override
    public void navigate(AppRoute route) {
        viewManager.navigate(route);
    }

    @Override
    public void navigateRoot(AppRoute route) {
        viewManager.navigateRoot(route);
    }

    @Override
    public void start(Stage primaryStage) {
        // Initialize Dagger 2 DI
        DiProvider.init();

        // Initialize SQLite Database schema if necessary
        initializeDatabase();

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
        getAppComponent().getSessionManager().logout();
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
     * record user sessions
     *
     * @param user user
     */
    public void recordSession(User user) {
        getAppComponent().getSessionManager().login(user);
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
     * Open the scene to edit detail for the specified user. If the user
     * clicks OK, the changes are saved to the database
     *
     * @param user the user object to be edited.
     */
    public void showUserEditScene(User user) {
        viewManager.showUserEditScene(user);
    }

    /**
     * Show laporan
     */
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
     * Showing secretary edit kegiatan
     */
    public void showKegiatanEditform(Kegiatan kegiatan) {
        viewManager.showKegiatanEditform(kegiatan);
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
     * Showing undangan edit form
     *
     * @param undangan undangan
     */
    public void showUndanganEditForm(TamuKegiatan undangan) {
        viewManager.showUndanganEditForm(undangan);
    }

    /**
     * show anakYatimprompt
     */
    public void editDonaturAnakYatim(DonasiAYatim model) {
        viewManager.editDonaturAnakYatim(model);
    }

    public void editAnakYatim(AnakYatim model) {
        viewManager.editAnakYatim(model);
    }

    /**
     * show zakatPrompt
     */


    public void editDonaturZakat(ZakatMasuk model) {
        viewManager.editDonaturZakat(model);
    }

    public void editPenerimaZakat(ZakatKeluar model) {
        viewManager.editPenerimaZakat(model);
    }

    /**
     * show Pembangunan
     */


    public void editDonaturPembangunan(DonasiPembangunan model) {
        viewManager.editDonaturPembangunan(model);
    }

    public void editAlokasiPembangunan(Pembangunan model) {
        viewManager.editAlokasiPembangunan(model);
    }

    /**
     * show operational
     */


    public void editDonaturOperasional(DonasiOperasional model) {
        viewManager.editDonaturOperasional(model);
    }

    public void editAlokasiOperasional(Operasional model) {
        viewManager.editAlokasiOperasional(model);
    }

    /**
     * show tpa
     */


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
        getAppComponent().getSessionManager().logout();

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
