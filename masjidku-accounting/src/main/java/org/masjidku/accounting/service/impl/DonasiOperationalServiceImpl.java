package org.masjidku.accounting.service.impl;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.masjidku.accounting.client.model.operasional.DonasiOperasional;
import org.masjidku.accounting.client.service.DonasiOperationalService;
import org.masjidku.accounting.domain.entity.DonasiOperasionalEntity;
import org.masjidku.accounting.domain.repository.DonasiOperasionalRepository;
import org.masjidku.accounting.domain.repository.impl.DonasiOperasionalRepositoryImpl;

import java.sql.SQLException;
import java.util.stream.Collectors;

public class DonasiOperationalServiceImpl implements DonasiOperationalService {
    private final DonasiOperasionalRepository repository = new DonasiOperasionalRepositoryImpl();

    private DonasiOperasionalEntity toEntity(DonasiOperasional model) {
        DonasiOperasionalEntity entity = new DonasiOperasionalEntity();
        entity.setId(model.getId());
        entity.setJumlah(model.getJumlah());
        entity.setTanggal(model.getTanggal());
        entity.setOperator(model.getOperator());
        entity.setDonatur(model.getDonatur());
        return entity;
    }

    private DonasiOperasional toModel(DonasiOperasionalEntity entity) {
        if (entity == null) return new DonasiOperasional();
        return new DonasiOperasional(entity.getId(), entity.getDonatur(), entity.getJumlah(), entity.getTanggal(), entity.getOperator());
    }

    @Override
    public DonasiOperasional get(String id) throws SQLException {
        return repository.findById(id).map(this::toModel).orElse(null);
    }

    @Override
    public ObservableList<DonasiOperasional> getAll() throws SQLException {
        return FXCollections.observableArrayList(
            repository.findAll().stream().map(this::toModel).collect(Collectors.toList())
        );
    }

    @Override
    public void save(DonasiOperasional model) throws SQLException {
        repository.save(toEntity(model));
    }

    @Override
    public void update(String[] params) throws SQLException {
        DonasiOperasionalEntity entity = repository.findById(params[0]).orElse(new DonasiOperasionalEntity());
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
    public DonasiOperasional getLastRecord() throws SQLException {
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
