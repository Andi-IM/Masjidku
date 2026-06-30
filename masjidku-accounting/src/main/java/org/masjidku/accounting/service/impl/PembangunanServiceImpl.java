package org.masjidku.accounting.service.impl;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.masjidku.accounting.client.model.pembangunan.Pembangunan;
import org.masjidku.accounting.client.service.PembangunanService;
import org.masjidku.accounting.domain.entity.PembangunanEntity;
import org.masjidku.accounting.domain.repository.PembangunanRepository;
import org.masjidku.accounting.domain.repository.impl.PembangunanRepositoryImpl;

import java.sql.SQLException;
import java.util.stream.Collectors;

public class PembangunanServiceImpl implements PembangunanService {
    private final PembangunanRepository repository = new PembangunanRepositoryImpl();

    private PembangunanEntity toEntity(Pembangunan model) {
        PembangunanEntity entity = new PembangunanEntity();
        entity.setId(model.getId());
        entity.setJumlah(model.getJumlah());
        entity.setTanggal(model.getTanggal());
        entity.setOperator(model.getOperator());
        entity.setTujuan(model.getTujuan());
        entity.setKeterangan(model.getKeterangan());
        return entity;
    }

    private Pembangunan toModel(PembangunanEntity entity) {
        if (entity == null) return new Pembangunan();
        return new Pembangunan(entity.getId(), entity.getTujuan(), entity.getKeterangan(), entity.getJumlah(), entity.getTanggal(), entity.getOperator());
    }

    @Override
    public Pembangunan get(String id) throws SQLException {
        return repository.findById(id).map(this::toModel).orElse(null);
    }

    @Override
    public ObservableList<Pembangunan> getAll() throws SQLException {
        return FXCollections.observableArrayList(
            repository.findAll().stream().map(this::toModel).collect(Collectors.toList())
        );
    }

    @Override
    public void save(Pembangunan model) throws SQLException {
        repository.save(toEntity(model));
    }

    @Override
    public void update(String[] params) throws SQLException {
        PembangunanEntity entity = repository.findById(params[0]).orElse(new PembangunanEntity());
        entity.setId(params[0]);
        entity.setTujuan(params[1]);
        entity.setKeterangan(params[2]);
        entity.setJumlah(params[3]);
        entity.setTanggal(params[4]);
        entity.setOperator(params[5]);
        repository.update(entity);
    }

    @Override
    public void delete(String id) throws SQLException {
        repository.delete(id);
    }

    @Override
    public Pembangunan getLastRecord() throws SQLException {
        return toModel(repository.getLastRecord());
    }

    @Override
    public String getTotalIncome() throws SQLException {
        return repository.getTotal();
    }

    @Override
    public boolean isDataExist(String id) throws SQLException {
        return repository.exists(id);
    }

    @Override
    public boolean getConnection() {
        return true; // Connection is managed by Hibernate
    }
}
