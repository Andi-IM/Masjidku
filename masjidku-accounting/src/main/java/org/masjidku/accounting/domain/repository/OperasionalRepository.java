package org.masjidku.accounting.domain.repository;

import org.masjidku.accounting.domain.entity.OperasionalEntity;
import java.util.List;
import java.util.Optional;

public interface OperasionalRepository {
    Optional<OperasionalEntity> findById(String id);
    List<OperasionalEntity> findAll();
    void save(OperasionalEntity entity);
    void update(OperasionalEntity entity);
    void delete(String id);
    boolean exists(String id);
    OperasionalEntity getLastRecord();
    String getTotal();
}
