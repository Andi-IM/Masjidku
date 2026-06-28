package org.masjidku.service;

import javafx.collections.ObservableList;
import org.masjidku.model.user.User;

import java.sql.SQLException;

/**
 * Abstraction layer for User business logic.
 * Separates the UI/Controllers from direct DAO/Database access.
 */
public interface UserService {
    
    boolean getConnection();
    
    ObservableList<User> getAll() throws SQLException;
    
    void save(User user) throws SQLException;
    
    void update(String[] params) throws SQLException;
    
    void update(String userid, String username, String password) throws SQLException;
    
    void delete(String userid) throws SQLException;
    
    boolean isReset(String userid) throws SQLException;
    
    void reset(String userId) throws SQLException;
    
    User get(String userid) throws SQLException;
    
    boolean isUserExist(String userid) throws SQLException;
    
    boolean isUserExist(String userid, String password) throws SQLException;
}
