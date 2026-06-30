package org.masjidku.accounting.domain.repository;

import org.masjidku.accounting.domain.entity.TpaMasukEntity;
import java.util.List;
import java.util.Optional;

public interface TpaMasukRepository {
    Optional<TpaMasukEntity> findById(String id);
    List<TpaMasukEntity> findAll();
    void save(TpaMasukEntity entity);
    void update(TpaMasukEntity entity);
    void delete(String id);
    boolean exists(String id);
    TpaMasukEntity getLastRecord();
    String getTotal();
}
