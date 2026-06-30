package org.masjidku.service.impl;

import javafx.collections.ObservableList;
import org.masjidku.auth.client.model.User;
import org.masjidku.auth.client.AuthClient;
import org.masjidku.service.UserService;
import org.masjidku.util.ServiceProvider;

/**
 * Implementation of the UserService abstraction layer.
 * This class handles the business logic and delegates data access to the DAO.
 */
public class UserServiceImpl implements UserService {

    private final AuthClient userDao;

    public UserServiceImpl() {
        this.userDao = ServiceProvider.get(AuthClient.class);
    }

    // Dependency Injection constructor (recommended for future testing)
    @SuppressWarnings("unused")
    public UserServiceImpl(AuthClient userDao) {
        this.userDao = userDao;
    }

    @Override
    public ObservableList<User> getAll() {
        return javafx.collections.FXCollections.observableArrayList(userDao.getAllUsers());
    }

    @Override
    public void save(User user) {
        // Business logic validations could go here before saving
        userDao.saveUser(user);
    }

    @Override
    public void update(String[] params) {
        userDao.updateUser(params);
    }

    @Override
    public void update(String userid, String username, String password) {
        userDao.updateUser(userid, username, password);
    }

    @Override
    public void delete(String userid) {
        userDao.deleteUser(userid);
    }

    @Override
    public boolean isReset(String userid) {
        return userDao.isUserReset(userid);
    }

    @Override
    public void reset(String userId) {
        userDao.resetUser(userId);
    }

    @Override
    public User get(String userid) {
        return userDao.getUser(userid);
    }

    @Override
    public boolean isUserExist(String userid) {
        return userDao.isUserExist(userid);
    }

    @Override
    public boolean isUserExist(String userid, String password) {
        return userDao.isUserExist(userid, password);
    }
}
