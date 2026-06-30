package org.masjidku.accounting.service.impl;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.masjidku.accounting.client.model.anakyatim.AnakYatim;
import org.masjidku.accounting.client.service.AnakYatimService;
import org.masjidku.accounting.domain.entity.AnakYatimEntity;
import org.masjidku.accounting.domain.repository.AnakYatimRepository;
import org.masjidku.accounting.domain.repository.impl.AnakYatimRepositoryImpl;

import java.sql.SQLException;
import java.util.stream.Collectors;

public class AnakYatimServiceImpl implements AnakYatimService {
    private final AnakYatimRepository repository = new AnakYatimRepositoryImpl();

    private AnakYatimEntity toEntity(AnakYatim model) {
        AnakYatimEntity entity = new AnakYatimEntity();
        entity.setId(model.getId());
        entity.setJumlah(model.getJumlah());
        entity.setTanggal(model.getTanggal());
        entity.setOperator(model.getOperator());
        entity.setTujuan(model.getTujuan());
        entity.setUsia(model.getUsia());
        return entity;
    }

    private AnakYatim toModel(AnakYatimEntity entity) {
        if (entity == null) return new AnakYatim();
        return new AnakYatim(entity.getId(), entity.getTujuan(), entity.getUsia(), entity.getJumlah(), entity.getTanggal(), entity.getOperator());
    }

    @Override
    public AnakYatim get(String id) throws SQLException {
        return repository.findById(id).map(this::toModel).orElse(null);
    }

    @Override
    public ObservableList<AnakYatim> getAll() throws SQLException {
        return FXCollections.observableArrayList(
            repository.findAll().stream().map(this::toModel).collect(Collectors.toList())
        );
    }

    @Override
    public void save(AnakYatim model) throws SQLException {
        repository.save(toEntity(model));
    }

    @Override
    public void update(String[] params) throws SQLException {
        AnakYatimEntity entity = repository.findById(params[0]).orElse(new AnakYatimEntity());
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
    public AnakYatim getLastRecord() throws SQLException {
        return toModel(repository.getLastRecord());
    }

    @Override
    public String getTotalOutcome() throws SQLException {
        return repository.getTotal();
    }

    @Override
    public boolean isAnakYatimExist(String id) throws SQLException {
        return repository.exists(id);
    }

    @Override
    public boolean getConnection() {
        return true;
    }
}
