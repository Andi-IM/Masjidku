package org.masjidku.accounting.service.impl;

import javafx.collections.ObservableList;
import org.masjidku.accounting.client.model.anakyatim.DonasiAYatim;
import org.masjidku.accounting.client.service.DonasiAYatimService;
import org.masjidku.accounting.dao.anakyatim.DonasiAYatimDao;

import java.sql.SQLException;

public class DonasiAYatimServiceImpl implements DonasiAYatimService {
    private final DonasiAYatimDao dao = new DonasiAYatimDao();

    @Override
    public DonasiAYatim get(String id) throws SQLException {
        return dao.get(id);
    }

    @Override
    public ObservableList<DonasiAYatim> getAll() throws SQLException {
        return dao.getAll();
    }

    @Override
    public void save(DonasiAYatim donasiAYatim) throws SQLException {
        dao.save(donasiAYatim);
    }

    @Override
    public void update(String[] params) throws SQLException {
        dao.update(params);
    }

    @Override
    public void delete(String id) { try { dao.delete(id); } catch(java.sql.SQLException e) { throw new RuntimeException(e); } }

    @Override
    public DonasiAYatim getLastRecord() throws SQLException {
        return dao.getLastRecord();
    }

    @Override
    public String getTotalIncome() throws SQLException {
        return dao.getTotalIncome();
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



