package org.masjidku.events.domain.repository;

import org.masjidku.events.domain.entity.Kegiatan;

import java.util.List;
import java.util.Optional;

public interface KegiatanRepository {
    Optional<Kegiatan> getKegiatanById(String id);

    List<Kegiatan> getAllKegiatan();

    void saveKegiatan(Kegiatan kegiatan);

    void updateKegiatan(Kegiatan kegiatan);

    void deleteKegiatan(String id);

    boolean exists(String id);

    List<String> getAllKegiatanNames();

    String getIdByName(String name);

    Kegiatan getLastKegiatan();

    int getTotalKegiatanCount();
}
