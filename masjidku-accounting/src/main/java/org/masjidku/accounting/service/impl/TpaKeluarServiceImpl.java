package org.masjidku.accounting.service.impl;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.masjidku.accounting.client.model.tpa.TpaKeluar;
import org.masjidku.accounting.client.service.TpaKeluarService;
import org.masjidku.accounting.domain.entity.TpaKeluarEntity;
import org.masjidku.accounting.domain.repository.TpaKeluarRepository;
import org.masjidku.accounting.domain.repository.impl.TpaKeluarRepositoryImpl;

import java.sql.SQLException;
import java.util.stream.Collectors;

public class TpaKeluarServiceImpl implements TpaKeluarService {
    private final TpaKeluarRepository repository = new TpaKeluarRepositoryImpl();

    private TpaKeluarEntity toEntity(TpaKeluar model) {
        TpaKeluarEntity entity = new TpaKeluarEntity();
        entity.setId(model.getId());
        entity.setJumlah(model.getJumlah());
        entity.setTanggal(model.getTanggal());
        entity.setOperator(model.getOperator());
        entity.setTujuan(model.getTujuan());
        entity.setKeterangan(model.getKeterangan());
        return entity;
    }

    private TpaKeluar toModel(TpaKeluarEntity entity) {
        if (entity == null) return new TpaKeluar();
        return new TpaKeluar(entity.getId(), entity.getTujuan(), entity.getKeterangan(), entity.getJumlah(), entity.getTanggal(), entity.getOperator());
    }

    @Override
    public TpaKeluar get(String id) throws SQLException {
        return repository.findById(id).map(this::toModel).orElse(null);
    }

    @Override
    public ObservableList<TpaKeluar> getAll() throws SQLException {
        return FXCollections.observableArrayList(
            repository.findAll().stream().map(this::toModel).collect(Collectors.toList())
        );
    }

    @Override
    public void save(TpaKeluar model) throws SQLException {
        repository.save(toEntity(model));
    }

    @Override
    public void update(String[] params) throws SQLException {
        TpaKeluarEntity entity = repository.findById(params[0]).orElse(new TpaKeluarEntity());
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
    public TpaKeluar getLastRecord() throws SQLException {
        return toModel(repository.getLastRecord());
    }

    @Override
    public String getTotalOutcome() throws SQLException {
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
