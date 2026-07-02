package org.masjidku.accounting.domain.repository;

import java.util.List;
import java.util.Optional;

public interface BaseRepository<T> {
    Optional<T> findById(String id);
    List<T> findAll();
    void save(T entity);
    void update(T entity);
    void delete(String id);
    boolean exists(String id);
    T getLastRecord();
    String getTotal();
}
