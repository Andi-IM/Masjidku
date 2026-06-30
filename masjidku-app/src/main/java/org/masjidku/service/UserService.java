package org.masjidku.service;

import javafx.collections.ObservableList;
import org.masjidku.model.user.User;

import java.util.List;

/**
 * Service interface for User entity.
 */
public interface UserService {
    /**
     * Check if connection to DB is established.
     * @return true if connected.
     */
    boolean getConnection();

    List<User> getAll();

    void save(User user);

    void update(String[] params);

    void update(String userid, String username, String password);

    void delete(String userid);

    boolean isReset(String userid);

    void reset(String userId);

    User get(String userid);

    boolean isUserExist(String userid);

    boolean isUserExist(String userid, String password);
}
