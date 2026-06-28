package org.masjidku.accounting.service.impl;
import org.masjidku.accounting.client.service.TpaKeluarService;
import org.masjidku.accounting.dao.tpa.TpaKeluarDao;
import javafx.collections.ObservableList;
import java.sql.SQLException;

import org.masjidku.accounting.client.model.tpa.*;

public class TpaKeluarServiceImpl implements TpaKeluarService {
    private final TpaKeluarDao dao = new TpaKeluarDao();
    @Override
    public TpaKeluar get(String id) throws SQLException {
        return dao.get(id);    }
    @Override
    public ObservableList<TpaKeluar> getAll() throws SQLException {
        return dao.getAll();    }
    @Override
    public void save(TpaKeluar tpaKeluar) throws SQLException {
        dao.save(tpaKeluar);    }
    @Override
    public void update(String[] params) throws SQLException {
        dao.update(params);    }
    @Override
    public void delete(String id) throws SQLException {
        dao.delete(id);    }
    @Override
    public TpaKeluar getLastRecord() throws SQLException {
        return dao.getLastRecord();    }
    @Override
    public String getTotalOutcome() throws SQLException {
        return dao.getTotalOutcome();    }
    @Override
    public boolean isDataExist(String id) throws SQLException {
        return dao.isDataExist(id);    }
    @Override
    public boolean getConnection() { return dao.getConnection(); }
}

