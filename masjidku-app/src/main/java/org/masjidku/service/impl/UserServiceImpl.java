package org.masjidku.service.impl;

import javafx.collections.ObservableList;
import org.masjidku.model.user.User;
import org.masjidku.model.user.dao.UserDao;
import org.masjidku.service.UserService;

import java.sql.SQLException;

/**
 * Implementation of the UserService abstraction layer.
 * This class handles the business logic and delegates data access to the DAO.
 */
public class UserServiceImpl implements UserService {

    private final UserDao userDao;

    public UserServiceImpl() {
        this.userDao = new UserDao();
    }

    // Dependency Injection constructor (recommended for future testing)
    @SuppressWarnings("unused")
    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public boolean getConnection() {
        return userDao.getConnection();
    }

    @Override
    public ObservableList<User> getAll() throws SQLException {
        return userDao.getAll();
    }

    @Override
    public void save(User user) throws SQLException {
        // Business logic validations could go here before saving
        userDao.save(user);
    }

    @Override
    public void update(String[] params) throws SQLException {
        userDao.update(params);
    }

    @Override
    public void update(String userid, String username, String password) throws SQLException {
        userDao.update(userid, username, password);
    }

    @Override
    public void delete(String userid) throws SQLException {
        userDao.delete(userid);
    }

    @Override
    public boolean isReset(String userid) throws SQLException {
        return userDao.isReset(userid);
    }

    @Override
    public void reset(String userId) throws SQLException {
        userDao.reset(userId);
    }

    @Override
    public User get(String userid) throws SQLException {
        return userDao.get(userid);
    }

    @Override
    public boolean isUserExist(String userid) throws SQLException {
        return userDao.isUserExist(userid);
    }

    @Override
    public boolean isUserExist(String userid, String password) throws SQLException {
        return userDao.isUserExist(userid, password);
    }
}
