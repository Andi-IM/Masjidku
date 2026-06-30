package org.masjidku.events.domain.repository;

import org.masjidku.events.domain.entity.TamuKegiatan;

import java.util.List;
import java.util.Optional;

public interface TamuKegiatanRepository {
    Optional<TamuKegiatan> getTamuKegiatanById(String id);

    List<TamuKegiatan> getAllTamuKegiatan();

    void save(TamuKegiatan undangan);

    void update(TamuKegiatan undangan);

    void delete(String id);

    boolean isUndanganExist(String id);
}
