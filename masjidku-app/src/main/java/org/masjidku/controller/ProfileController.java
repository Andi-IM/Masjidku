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

package org.masjidku.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import org.masjidku.navigation.AppRouter;
import org.masjidku.auth.client.model.UserProfile;
import org.masjidku.auth.client.AuthClient;
import org.masjidku.util.ServiceProvider;

import static org.masjidku.di.DiProvider.getAppComponent;


public class ProfileController {
    private static final Logger log = LoggerFactory.getLogger(ProfileController.class);

    @FXML
    private Label userId;
    @FXML
    private Label username;
    @FXML
    private Label userRole;
    @FXML
    private Label userPhoneNum;
    @FXML
    private Label userAddress;
    @FXML
    public Label userStatus;
    @FXML
    public Label userLastUpdate;

    private AppRouter mainApp;
    private UserProfile profile;
    /**
     * get User Data from DAO.
     *
     * @return Observable List
     */
    private UserProfile getUserData(String userid) {
        AuthClient dao = ServiceProvider.get(AuthClient.class);
        return dao.getFullUserData(userid);
    }

    public void setMainApp(AppRouter mainApp) {
        String userid = getAppComponent().getSessionManager().getCurrentUser().id();
        this.mainApp = mainApp;
        profile = getUserData(userid);

        if (profile!=null){
            userId.setText(profile.user().id());
            username.setText(profile.user().username());
            userRole.setText(profile.user().jabatan());
            userStatus.setText(profile.user().status());
            userLastUpdate.setText(profile.user().updatedAt());
            userPhoneNum.setText(profile.notelp());
            userAddress.setText(profile.alamat());
        }
    }

    @FXML
    public void onEditProfile() {
        mainApp.editProfile(profile);
    }

    @FXML
    public void onLogoutClick() {
        mainApp.onLogoutAction();
    }
}


