package org.masjidku.accounting.service.impl;
import org.masjidku.accounting.client.service.ZakatKeluarService;
import org.masjidku.accounting.dao.zakat.ZakatKeluarDao;
import javafx.collections.ObservableList;
import java.sql.SQLException;

import org.masjidku.accounting.client.model.zakat.*;
public class ZakatKeluarServiceImpl implements ZakatKeluarService {
    private final ZakatKeluarDao dao = new ZakatKeluarDao();
    @Override
    public ZakatKeluar get(String id) throws SQLException {
        return dao.get(id);    }
    @Override
    public ObservableList<ZakatKeluar> getAll() throws SQLException {
        return dao.getAll();    }
    @Override
    public void save(ZakatKeluar zakatKeluar) throws SQLException {
        dao.save(zakatKeluar);    }
    @Override
    public void update(String[] params) throws SQLException {
        dao.update(params);    }
    @Override
    public void delete(String id) throws SQLException {
        dao.delete(id);    }
    @Override
    public ZakatKeluar getLastRecord() throws SQLException {
        return dao.getLastRecord();    }
    @Override
    public String gettotalOutcome() throws SQLException {
        return dao.gettotalOutcome();    }
    @Override
    public boolean isDataExist(String id) throws SQLException {
        return dao.isDataExist(id);    }
    @Override
    public boolean getConnection() { return dao.getConnection(); }
}
