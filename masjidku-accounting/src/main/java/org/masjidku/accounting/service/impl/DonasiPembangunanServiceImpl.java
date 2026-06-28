package org.masjidku.accounting.service.impl;

import javafx.collections.ObservableList;
import org.masjidku.accounting.client.model.pembangunan.DonasiPembangunan;
import org.masjidku.accounting.client.service.DonasiPembangunanService;
import org.masjidku.accounting.dao.pembangunan.DonasiPembangunanDao;

import java.sql.SQLException;

public class DonasiPembangunanServiceImpl implements DonasiPembangunanService {
    private final DonasiPembangunanDao dao = new DonasiPembangunanDao();

    @Override
    public DonasiPembangunan get(String id) { try { return dao.get(id); } catch(java.sql.SQLException e) { throw new RuntimeException(e); } }

    @Override
    public ObservableList<DonasiPembangunan> getAll() throws SQLException {
        return dao.getAll();
    }

    @Override
    public void save(DonasiPembangunan donasiPembangunan) throws SQLException {
        dao.save(donasiPembangunan);
    }

    @Override
    public void update(String[] params) throws SQLException {
        dao.update(params);
    }

    @Override
    public void delete(String id) throws SQLException {
        dao.delete(id);
    }

    @Override
    public DonasiPembangunan getLastRecord() throws SQLException {
        return dao.getLastRecord();
    }

    @Override
    public String getTotalOutcome() throws SQLException {
        return dao.getTotalOutcome();
    }

    @Override
    public boolean isDonaturExist(String id) throws SQLException {
        return dao.isDonaturExist(id);
    }

    @Override
    public boolean getConnection() {
        return dao.getConnection();
    }
}

