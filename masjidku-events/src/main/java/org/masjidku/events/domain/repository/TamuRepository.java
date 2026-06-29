package org.masjidku.events.domain.repository;
import javafx.collections.ObservableList;
import org.masjidku.events.domain.entity.Tamu;

public interface TamuRepository {
    Tamu get(String id);
    ObservableList<Tamu> getAll();
    void save(Tamu tamu);
    void update(String[] params);
    void delete(String id);
    boolean isTamuExist(String id);
    ObservableList<String> getAllTamuName();
    String getIdByName(String name);
}
