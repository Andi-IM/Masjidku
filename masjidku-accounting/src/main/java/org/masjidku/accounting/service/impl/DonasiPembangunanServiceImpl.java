package org.masjidku.accounting.service.impl;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.masjidku.accounting.client.model.pembangunan.DonasiPembangunan;
import org.masjidku.accounting.client.service.DonasiPembangunanService;
import org.masjidku.accounting.domain.entity.DonasiPembangunanEntity;
import org.masjidku.accounting.domain.repository.DonasiPembangunanRepository;
import org.masjidku.accounting.domain.repository.impl.DonasiPembangunanRepositoryImpl;

import java.sql.SQLException;
import java.util.stream.Collectors;

public class DonasiPembangunanServiceImpl implements DonasiPembangunanService {
    private final DonasiPembangunanRepository repository = new DonasiPembangunanRepositoryImpl();

    private DonasiPembangunanEntity toEntity(DonasiPembangunan model) {
        DonasiPembangunanEntity entity = new DonasiPembangunanEntity();
        entity.setId(model.getId());
        entity.setJumlah(model.getJumlah());
        entity.setTanggal(model.getTanggal());
        entity.setOperator(model.getOperator());
        entity.setDonatur(model.getDonatur());
        return entity;
    }

    private DonasiPembangunan toModel(DonasiPembangunanEntity entity) {
        if (entity == null) return new DonasiPembangunan();
        return new DonasiPembangunan(entity.getId(), entity.getDonatur(), entity.getJumlah(), entity.getTanggal(), entity.getOperator());
    }

    @Override
    public DonasiPembangunan get(String id) {
        return repository.findById(id).map(this::toModel).orElse(null);
    }

    @Override
    public ObservableList<DonasiPembangunan> getAll() throws SQLException {
        return FXCollections.observableArrayList(
            repository.findAll().stream().map(this::toModel).collect(Collectors.toList())
        );
    }

    @Override
    public void save(DonasiPembangunan model) throws SQLException {
        repository.save(toEntity(model));
    }

    @Override
    public void update(String[] params) throws SQLException {
        DonasiPembangunanEntity entity = repository.findById(params[0]).orElse(new DonasiPembangunanEntity());
        entity.setId(params[0]);
        entity.setDonatur(params[1]);
        entity.setJumlah(params[2]);
        entity.setTanggal(params[3]);
        entity.setOperator(params[4]);
        repository.update(entity);
    }

    @Override
    public void delete(String id) throws SQLException {
        repository.delete(id);
    }

    @Override
    public DonasiPembangunan getLastRecord() throws SQLException {
        return toModel(repository.getLastRecord());
    }

    @Override
    public String getTotalOutcome() throws SQLException {
        return repository.getTotal();
    }

    @Override
    public boolean isDonaturExist(String id) throws SQLException {
        return repository.exists(id);
    }

    @Override
    public boolean getConnection() {
        return true; // Connection is managed by Hibernate
    }
}
