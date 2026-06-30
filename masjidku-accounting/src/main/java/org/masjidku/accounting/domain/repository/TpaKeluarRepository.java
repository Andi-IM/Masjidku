package org.masjidku.accounting.domain.repository;

import org.masjidku.accounting.domain.entity.TpaKeluarEntity;
import java.util.List;
import java.util.Optional;

public interface TpaKeluarRepository {
    Optional<TpaKeluarEntity> findById(String id);
    List<TpaKeluarEntity> findAll();
    void save(TpaKeluarEntity entity);
    void update(TpaKeluarEntity entity);
    void delete(String id);
    boolean exists(String id);
    TpaKeluarEntity getLastRecord();
    String getTotal();
}
