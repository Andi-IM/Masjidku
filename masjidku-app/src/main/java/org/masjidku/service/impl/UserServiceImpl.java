package org.masjidku.service.impl;

import javafx.collections.ObservableList;
import org.masjidku.model.user.User;
import org.masjidku.domain.repository.UserRepository;
import org.masjidku.domain.repository.impl.UserRepositoryImpl;
import org.masjidku.service.UserService;

/**
 * Implementation of the UserService abstraction layer.
 * This class handles the business logic and delegates data access to the DAO.
 */
public class UserServiceImpl implements UserService {

    private final UserRepository userDao;

    public UserServiceImpl() {
        this.userDao = new UserRepositoryImpl();
    }

    // Dependency Injection constructor (recommended for future testing)
    @SuppressWarnings("unused")
    public UserServiceImpl(UserRepository userDao) {
        this.userDao = userDao;
    }

    @Override
    public ObservableList<User> getAll() {
        return javafx.collections.FXCollections.observableArrayList(userDao.getAll());
    }

    @Override
    public void save(User user) {
        // Business logic validations could go here before saving
        userDao.save(user);
    }

    @Override
    public void update(String[] params) {
        userDao.update(params);
    }

    @Override
    public void update(String userid, String username, String password) {
        userDao.update(userid, username, password);
    }

    @Override
    public void delete(String userid) {
        userDao.delete(userid);
    }

    @Override
    public boolean isReset(String userid) {
        return userDao.isReset(userid);
    }

    @Override
    public void reset(String userId) {
        userDao.reset(userId);
    }

    @Override
    public User get(String userid) {
        return userDao.get(userid);
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
