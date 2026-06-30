package org.masjidku.accounting.domain.repository;

import org.masjidku.accounting.domain.entity.DonasiAnakYatimEntity;
import java.util.List;
import java.util.Optional;

public interface DonasiAnakYatimRepository {
    Optional<DonasiAnakYatimEntity> findById(String id);
    List<DonasiAnakYatimEntity> findAll();
    void save(DonasiAnakYatimEntity entity);
    void update(DonasiAnakYatimEntity entity);
    void delete(String id);
    boolean exists(String id);
    DonasiAnakYatimEntity getLastRecord();
    String getTotal();
}
