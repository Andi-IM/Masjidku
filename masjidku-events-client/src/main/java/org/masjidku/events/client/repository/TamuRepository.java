package org.masjidku.events.client.repository;

import javafx.collections.ObservableList;
import org.masjidku.events.client.model.Tamu;

public interface TamuRepository {
    Tamu get(String id);
    ObservableList<Tamu> getAll();
    void save(Tamu model);
    void update(String[] params);
    void delete(String id);
    boolean isTamuExist(String id);
    ObservableList<String> getAllTamuName();
    String getIdByName(String name);
}
