package org.masjidku.events.domain.repository;
import javafx.collections.ObservableList;
import org.masjidku.events.client.model.Tamu;
import java.sql.SQLException;
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
