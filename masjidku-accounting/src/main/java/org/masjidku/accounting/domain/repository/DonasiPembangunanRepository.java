package org.masjidku.accounting.domain.repository;

import org.masjidku.accounting.domain.entity.DonasiPembangunanEntity;
import java.util.List;
import java.util.Optional;

public interface DonasiPembangunanRepository {
    Optional<DonasiPembangunanEntity> findById(String id);
    List<DonasiPembangunanEntity> findAll();
    void save(DonasiPembangunanEntity entity);
    void update(DonasiPembangunanEntity entity);
    void delete(String id);
    boolean exists(String id);
    DonasiPembangunanEntity getLastRecord();
    String getTotal();
}
