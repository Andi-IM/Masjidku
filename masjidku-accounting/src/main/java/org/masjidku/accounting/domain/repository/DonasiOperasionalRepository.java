package org.masjidku.accounting.domain.repository;

import org.masjidku.accounting.domain.entity.DonasiOperasionalEntity;
import java.util.List;
import java.util.Optional;

public interface DonasiOperasionalRepository {
    Optional<DonasiOperasionalEntity> findById(String id);
    List<DonasiOperasionalEntity> findAll();
    void save(DonasiOperasionalEntity entity);
    void update(DonasiOperasionalEntity entity);
    void delete(String id);
    boolean exists(String id);
    DonasiOperasionalEntity getLastRecord();
    String getTotal();
}
