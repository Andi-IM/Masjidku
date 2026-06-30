package org.masjidku.events.domain.repository;

import org.masjidku.events.domain.entity.Tamu;

import java.util.List;
import java.util.Optional;

public interface TamuRepository {
    Optional<Tamu> get(String id);

    List<Tamu> getAll();

    void save(Tamu tamu);

    void update(Tamu tamu);

    void delete(String id);

    boolean isTamuExist(String id);

    List<String> getAllTamuName();

    String getIdByName(String name);
}
