package org.masjidku.accounting.service.impl;

import org.masjidku.accounting.client.service.TpaMasukService;
import org.masjidku.accounting.dao.tpa.TpaMasukDao;
import javafx.collections.ObservableList;
import java.sql.SQLException;
import java.util.List;
import org.masjidku.accounting.client.model.*;
import org.masjidku.accounting.client.model.anakyatim.*;
import org.masjidku.accounting.client.model.operasional.*;
import org.masjidku.accounting.client.model.pembangunan.*;
import org.masjidku.accounting.client.model.tpa.*;
import org.masjidku.accounting.client.model.zakat.*;

public class TpaMasukServiceImpl implements TpaMasukService {
    private final TpaMasukDao dao = new TpaMasukDao();

    @Override
    public TpaMasuk get(String id) throws SQLException {
        return dao.get(id);    }
    @Override
    public ObservableList<TpaMasuk> getAll() throws SQLException {
        return dao.getAll();    }
    @Override
    public void save(TpaMasuk tpaMasuk) throws SQLException {
        dao.save(tpaMasuk);    }
    @Override
    public void update(String[] params) throws SQLException {
        dao.update(params);    }
    @Override
    public void delete(String id) throws SQLException {
        dao.delete(id);    }
    @Override
    public TpaMasuk getLastRecord() throws SQLException {
        return dao.getLastRecord();    }
    @Override
    public String getTotalIncome() throws SQLException {
        return dao.getTotalIncome();    }
    @Override
    public boolean isDonaturExist(String id) throws SQLException {
        return dao.isDonaturExist(id);    }

    @Override
    public boolean getConnection() { return dao.getConnection(); }
}
