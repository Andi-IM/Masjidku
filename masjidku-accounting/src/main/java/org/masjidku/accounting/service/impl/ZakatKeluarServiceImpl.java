package org.masjidku.accounting.service.impl;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.masjidku.accounting.client.model.zakat.ZakatKeluar;
import org.masjidku.accounting.client.service.ZakatKeluarService;
import org.masjidku.accounting.domain.entity.ZakatKeluarEntity;
import org.masjidku.accounting.domain.repository.ZakatKeluarRepository;
import org.masjidku.accounting.domain.repository.impl.ZakatKeluarRepositoryImpl;

import java.sql.SQLException;
import java.util.stream.Collectors;

public class ZakatKeluarServiceImpl implements ZakatKeluarService {
    private final ZakatKeluarRepository repository = new ZakatKeluarRepositoryImpl();

    private ZakatKeluarEntity toEntity(ZakatKeluar model) {
        ZakatKeluarEntity entity = new ZakatKeluarEntity();
        entity.setId(model.getId());
        entity.setJumlah(model.getJumlah());
        entity.setTanggal(model.getTanggal());
        entity.setOperator(model.getOperator());
        entity.setTujuan(model.getTujuan());
        entity.setKeterangan(model.getKeterangan());
        return entity;
    }

    private ZakatKeluar toModel(ZakatKeluarEntity entity) {
        if (entity == null) return new ZakatKeluar();
        return new ZakatKeluar(entity.getId(), entity.getTujuan(), entity.getJumlah(), entity.getTanggal(), entity.getOperator());
    }

    @Override
    public ZakatKeluar get(String id) throws SQLException {
        return repository.findById(id).map(this::toModel).orElse(null);
    }

    @Override
    public ObservableList<ZakatKeluar> getAll() throws SQLException {
        return FXCollections.observableArrayList(
            repository.findAll().stream().map(this::toModel).collect(Collectors.toList())
        );
    }

    @Override
    public void save(ZakatKeluar model) throws SQLException {
        repository.save(toEntity(model));
    }

    @Override
    public void update(String[] params) throws SQLException {
        ZakatKeluarEntity entity = repository.findById(params[0]).orElse(new ZakatKeluarEntity());
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
    public ZakatKeluar getLastRecord() throws SQLException {
        return toModel(repository.getLastRecord());
    }

    @Override
    public String gettotalOutcome() throws SQLException {
        return repository.getTotal();
    }

    @Override
    public boolean isDataExist(String id) throws SQLException {
        return repository.exists(id);
    }

    @Override
    public boolean getConnection() {
        return true;
    }
}
