package org.masjidku.accounting.domain.repository;

import org.masjidku.accounting.domain.entity.PembangunanEntity;
import java.util.List;
import java.util.Optional;

public interface PembangunanRepository {
    Optional<PembangunanEntity> findById(String id);
    List<PembangunanEntity> findAll();
    void save(PembangunanEntity entity);
    void update(PembangunanEntity entity);
    void delete(String id);
    boolean exists(String id);
    PembangunanEntity getLastRecord();
    String getTotal();
}
