package org.masjidku.events.client.service;

import javafx.collections.ObservableList;
import org.masjidku.events.client.model.Tamu;

public interface TamuService {
    Tamu get(String id) throws java.sql.SQLException;

    ObservableList<Tamu> getAll() throws java.sql.SQLException;

    void save(Tamu model) throws java.sql.SQLException;

    void update(String[] params) throws java.sql.SQLException;

    void delete(String id) throws java.sql.SQLException;

    boolean isTamuExist(String id) throws java.sql.SQLException;

    ObservableList<String> getAllTamuName() throws java.sql.SQLException;

    String getIdByName(String name) throws java.sql.SQLException;
}
