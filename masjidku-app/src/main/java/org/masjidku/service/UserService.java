package org.masjidku.service;

import org.masjidku.auth.client.model.User;

import java.util.List;

/**
 * Service interface for User entity.
 */
public interface UserService {

    List<User> getAll();

    void save(User user);

    void update(org.masjidku.auth.client.dto.UpdateUserStatusDto dto);

    void update(org.masjidku.auth.client.dto.UpdateUserCredentialsDto dto);

    void delete(String userid);

    boolean isReset(String userid);

    void reset(String userId);

    User get(String userid);

    boolean isUserExist(String userid);

    boolean isUserExist(String userid, String password);
}
