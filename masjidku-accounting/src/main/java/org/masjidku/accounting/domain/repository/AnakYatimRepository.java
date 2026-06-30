package org.masjidku.accounting.domain.repository;

import org.masjidku.accounting.domain.entity.AnakYatimEntity;
import java.util.List;
import java.util.Optional;

public interface AnakYatimRepository {
    Optional<AnakYatimEntity> findById(String id);
    List<AnakYatimEntity> findAll();
    void save(AnakYatimEntity entity);
    void update(AnakYatimEntity entity);
    void delete(String id);
    boolean exists(String id);
    AnakYatimEntity getLastRecord();
    String getTotal();
}
