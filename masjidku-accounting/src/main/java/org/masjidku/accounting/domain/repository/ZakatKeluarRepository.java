package org.masjidku.accounting.domain.repository;

import org.masjidku.accounting.domain.entity.ZakatKeluarEntity;
import java.util.List;
import java.util.Optional;

public interface ZakatKeluarRepository {
    Optional<ZakatKeluarEntity> findById(String id);
    List<ZakatKeluarEntity> findAll();
    void save(ZakatKeluarEntity entity);
    void update(ZakatKeluarEntity entity);
    void delete(String id);
    boolean exists(String id);
    ZakatKeluarEntity getLastRecord();
    String getTotal();
}
