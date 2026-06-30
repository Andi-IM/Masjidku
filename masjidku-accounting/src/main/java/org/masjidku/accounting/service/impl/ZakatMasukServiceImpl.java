package org.masjidku.accounting.service.impl;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.masjidku.accounting.client.model.zakat.ZakatMasuk;
import org.masjidku.accounting.client.service.ZakatMasukService;
import org.masjidku.accounting.domain.entity.ZakatMasukEntity;
import org.masjidku.accounting.domain.repository.ZakatMasukRepository;
import org.masjidku.accounting.domain.repository.impl.ZakatMasukRepositoryImpl;

import java.sql.SQLException;
import java.util.stream.Collectors;

public class ZakatMasukServiceImpl implements ZakatMasukService {
    private final ZakatMasukRepository repository = new ZakatMasukRepositoryImpl();

    private ZakatMasukEntity toEntity(ZakatMasuk model) {
        ZakatMasukEntity entity = new ZakatMasukEntity();
        entity.setId(model.getId());
        entity.setJumlah(model.getJumlah());
        entity.setTanggal(model.getTanggal());
        entity.setOperator(model.getOperator());
        entity.setDonatur(model.getDonatur());
        return entity;
    }

    private ZakatMasuk toModel(ZakatMasukEntity entity) {
        if (entity == null) return new ZakatMasuk();
        return new ZakatMasuk(entity.getId(), entity.getDonatur(), entity.getJumlah(), entity.getTanggal(), entity.getOperator());
    }

    @Override
    public ZakatMasuk get(String id) throws SQLException {
        return repository.findById(id).map(this::toModel).orElse(null);
    }

    @Override
    public ObservableList<ZakatMasuk> getAll() throws SQLException {
        return FXCollections.observableArrayList(
            repository.findAll().stream().map(this::toModel).collect(Collectors.toList())
        );
    }

    @Override
    public void save(ZakatMasuk model) throws SQLException {
        repository.save(toEntity(model));
    }

    @Override
    public void update(String[] params) throws SQLException {
        ZakatMasukEntity entity = repository.findById(params[0]).orElse(new ZakatMasukEntity());
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
    public ZakatMasuk getLastRecord() throws SQLException {
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
