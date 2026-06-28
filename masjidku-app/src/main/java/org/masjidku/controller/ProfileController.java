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

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import org.masjidku.MainApp;
import org.masjidku.model.user.UserProfile;
import org.masjidku.model.user.UserProfileDao;

import java.sql.SQLException;

public class ProfileController {

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

    private MainApp mainApp;
    private UserProfile profile;
    /**
     * get User Data from DAO.
     *
     * @return Observable List
     */
    private UserProfile getUserData(String userid) {
        UserProfileDao dao = new UserProfileDao();
        if (dao.getConnection()){
            try {
                return dao.getFullUserData(userid);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public void setMainApp(MainApp mainApp, String userid) {
        this.mainApp = mainApp;
        profile = getUserData(userid);

        if (profile!=null){
            userId.setText(profile.getUser().getUserId());
            username.setText(profile.getUser().getUsername());
            userRole.setText(profile.getUser().getJabatan().toString);
            userStatus.setText(profile.getUser().getStatus());
            userLastUpdate.setText(profile.getUser().getUpdated_at());
            userPhoneNum.setText(profile.getNotelp());
            userAddress.setText(profile.getAlamat());
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
