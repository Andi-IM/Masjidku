package org.masjidku.accounting.service.impl;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.masjidku.accounting.client.model.tpa.TpaMasuk;
import org.masjidku.accounting.client.service.TpaMasukService;
import org.masjidku.accounting.domain.entity.TpaMasukEntity;
import org.masjidku.accounting.domain.repository.TpaMasukRepository;
import org.masjidku.accounting.domain.repository.impl.TpaMasukRepositoryImpl;

import java.sql.SQLException;
import java.util.stream.Collectors;

public class TpaMasukServiceImpl implements TpaMasukService {
    private final TpaMasukRepository repository = new TpaMasukRepositoryImpl();

    private TpaMasukEntity toEntity(TpaMasuk model) {
        TpaMasukEntity entity = new TpaMasukEntity();
        entity.setId(model.getId());
        entity.setJumlah(model.getJumlah());
        entity.setTanggal(model.getTanggal());
        entity.setOperator(model.getOperator());
        entity.setDonatur(model.getDonatur());
        return entity;
    }

    private TpaMasuk toModel(TpaMasukEntity entity) {
        if (entity == null) return new TpaMasuk();
        return new TpaMasuk(entity.getId(), entity.getDonatur(), entity.getJumlah(), entity.getTanggal(), entity.getOperator());
    }

    @Override
    public TpaMasuk get(String id) throws SQLException {
        return repository.findById(id).map(this::toModel).orElse(null);
    }

    @Override
    public ObservableList<TpaMasuk> getAll() throws SQLException {
        return FXCollections.observableArrayList(
            repository.findAll().stream().map(this::toModel).collect(Collectors.toList())
        );
    }

    @Override
    public void save(TpaMasuk model) throws SQLException {
        repository.save(toEntity(model));
    }

    @Override
    public void update(String[] params) throws SQLException {
        TpaMasukEntity entity = repository.findById(params[0]).orElse(new TpaMasukEntity());
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
    public TpaMasuk getLastRecord() throws SQLException {
        return toModel(repository.getLastRecord());
    }

    @Override
    public String getTotalIncome() throws SQLException {
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
