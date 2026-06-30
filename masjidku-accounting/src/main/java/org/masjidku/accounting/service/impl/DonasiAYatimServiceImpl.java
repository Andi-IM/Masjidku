package org.masjidku.accounting.service.impl;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.masjidku.accounting.client.model.anakyatim.DonasiAYatim;
import org.masjidku.accounting.client.service.DonasiAYatimService;
import org.masjidku.accounting.domain.entity.DonasiAnakYatimEntity;
import org.masjidku.accounting.domain.repository.DonasiAnakYatimRepository;
import org.masjidku.accounting.domain.repository.impl.DonasiAnakYatimRepositoryImpl;

import java.sql.SQLException;
import java.util.stream.Collectors;

public class DonasiAYatimServiceImpl implements DonasiAYatimService {
    private final DonasiAnakYatimRepository repository = new DonasiAnakYatimRepositoryImpl();

    private DonasiAnakYatimEntity toEntity(DonasiAYatim model) {
        DonasiAnakYatimEntity entity = new DonasiAnakYatimEntity();
        entity.setId(model.getId());
        entity.setJumlah(model.getJumlah());
        entity.setTanggal(model.getTanggal());
        entity.setOperator(model.getOperator());
        entity.setDonatur(model.getDonatur());
        return entity;
    }

    private DonasiAYatim toModel(DonasiAnakYatimEntity entity) {
        if (entity == null) return new DonasiAYatim();
        return new DonasiAYatim(entity.getId(), entity.getDonatur(), entity.getJumlah(), entity.getTanggal(), entity.getOperator());
    }

    @Override
    public DonasiAYatim get(String id) throws SQLException {
        return repository.findById(id).map(this::toModel).orElse(null);
    }

    @Override
    public ObservableList<DonasiAYatim> getAll() throws SQLException {
        return FXCollections.observableArrayList(
            repository.findAll().stream().map(this::toModel).collect(Collectors.toList())
        );
    }

    @Override
    public void save(DonasiAYatim model) throws SQLException {
        repository.save(toEntity(model));
    }

    @Override
    public void update(String[] params) throws SQLException {
        DonasiAnakYatimEntity entity = repository.findById(params[0]).orElse(new DonasiAnakYatimEntity());
        entity.setId(params[0]);
        entity.setDonatur(params[1]);
        entity.setJumlah(params[2]);
        entity.setTanggal(params[3]);
        entity.setOperator(params[4]);
        repository.update(entity);
    }

    @Override
    public void delete(String id) {
        try {
            repository.delete(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public DonasiAYatim getLastRecord() throws SQLException {
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
