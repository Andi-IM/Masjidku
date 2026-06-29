package org.masjidku.accounting.service.impl;
import org.masjidku.accounting.client.service.PembangunanService;
import org.masjidku.accounting.dao.pembangunan.PembangunanDao;
import javafx.collections.ObservableList;
import java.sql.SQLException;

import org.masjidku.accounting.client.model.pembangunan.*;

public class PembangunanServiceImpl implements PembangunanService {
    private final PembangunanDao dao = new PembangunanDao();
    @Override
    public Pembangunan get(String id) throws SQLException {
        return dao.get(id);    }
    @Override
    public ObservableList<Pembangunan> getAll() throws SQLException {
        return dao.getAll();    }
    @Override
    public void save(Pembangunan pembangunan) throws SQLException {
        dao.save(pembangunan);    }
    @Override
    public void update(String[] params) throws SQLException {
        dao.update(params);    }
    @Override
    public void delete(String id) throws SQLException {
        dao.delete(id);    }
    @Override
    public Pembangunan getLastRecord() throws SQLException {
        return dao.getLastRecord();    }
    @Override
    public String getTotalIncome() throws SQLException {
        return dao.getTotalIncome();    }
    @Override
    public boolean isDataExist(String id) throws SQLException {
        return dao.isDataExist(id);    }
    @Override
    public boolean getConnection() { return dao.getConnection(); }
}

