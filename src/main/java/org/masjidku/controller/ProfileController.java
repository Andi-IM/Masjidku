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

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import org.masjidku.MainApp;
import org.masjidku.model.user.UserProfile;
import org.masjidku.model.user.UserProfileDao;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

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

    private MainApp mainApp;
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
        UserProfile profile = getUserData(userid);

        if (profile!=null){
            userId.setText(profile.getUser().getUserId());
            username.setText(profile.getUser().getUsername());
            userRole.setText(profile.getUser().getJabatan().toString);
            userPhoneNum.setText(profile.getNotelp());
            userAddress.setText(profile.getAlamat());
        }
    }

    @FXML
    public void onEditProfile() {
        mainApp.editProfile();
    }

    @FXML
    public void onLogoutClick() {
        mainApp.onLogoutAction();
    }
}
