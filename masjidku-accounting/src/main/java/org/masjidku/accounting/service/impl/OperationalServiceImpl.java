package org.masjidku.accounting.service.impl;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.masjidku.accounting.client.model.operasional.Operasional;
import org.masjidku.accounting.client.service.OperationalService;
import org.masjidku.accounting.domain.entity.OperasionalEntity;
import org.masjidku.accounting.domain.repository.OperasionalRepository;
import org.masjidku.accounting.domain.repository.impl.OperasionalRepositoryImpl;

import java.sql.SQLException;
import java.util.stream.Collectors;

public class OperationalServiceImpl implements OperationalService {
    private final OperasionalRepository repository = new OperasionalRepositoryImpl();

    private OperasionalEntity toEntity(Operasional model) {
        OperasionalEntity entity = new OperasionalEntity();
        entity.setId(model.getId());
        entity.setJumlah(model.getJumlah());
        entity.setTanggal(model.getTanggal());
        entity.setOperator(model.getOperator());
        entity.setNama(model.getTujuan());
        entity.setKeterangan(model.getKeterangan());
        return entity;
    }

    private Operasional toModel(OperasionalEntity entity) {
        if (entity == null) return new Operasional();
        return new Operasional(entity.getId(), entity.getNama(), entity.getKeterangan(), entity.getJumlah(), entity.getTanggal(), entity.getOperator());
    }

    @Override
    public Operasional get(String id) throws SQLException {
        return repository.findById(id).map(this::toModel).orElse(null);
    }

    @Override
    public ObservableList<Operasional> getAll() throws SQLException {
        return FXCollections.observableArrayList(
            repository.findAll().stream().map(this::toModel).collect(Collectors.toList())
        );
    }

    @Override
    public void save(Operasional model) throws SQLException {
        repository.save(toEntity(model));
    }

    @Override
    public void update(String[] params) throws SQLException {
        OperasionalEntity entity = repository.findById(params[0]).orElse(new OperasionalEntity());
        entity.setId(params[0]);
        entity.setNama(params[1]);
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
    public Operasional getLastRecord() throws SQLException {
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
