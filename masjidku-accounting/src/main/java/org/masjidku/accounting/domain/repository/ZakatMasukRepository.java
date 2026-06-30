package org.masjidku.accounting.domain.repository;

import org.masjidku.accounting.domain.entity.ZakatMasukEntity;
import java.util.List;
import java.util.Optional;

public interface ZakatMasukRepository {
    Optional<ZakatMasukEntity> findById(String id);
    List<ZakatMasukEntity> findAll();
    void save(ZakatMasukEntity entity);
    void update(ZakatMasukEntity entity);
    void delete(String id);
    boolean exists(String id);
    ZakatMasukEntity getLastRecord();
    String getTotal();
}
