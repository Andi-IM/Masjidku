package org.masjidku.accounting.service.impl;

import org.masjidku.accounting.client.service.OperationalService;
import org.masjidku.accounting.dao.operasional.OperationalDao;
import javafx.collections.ObservableList;
import java.sql.SQLException;
import java.util.List;
import org.masjidku.accounting.client.model.*;
import org.masjidku.accounting.client.model.anakyatim.*;
import org.masjidku.accounting.client.model.operasional.*;
import org.masjidku.accounting.client.model.pembangunan.*;
import org.masjidku.accounting.client.model.tpa.*;
import org.masjidku.accounting.client.model.zakat.*;

public class OperationalServiceImpl implements OperationalService {
    private final OperationalDao dao = new OperationalDao();

    @Override
    public Operasional get(String id) throws SQLException {
        return dao.get(id);    }
    @Override
    public ObservableList<Operasional> getAll() throws SQLException {
        return dao.getAll();    }
    @Override
    public void save(Operasional operasional) throws SQLException {
        dao.save(operasional);    }
    @Override
    public void update(String[] params) throws SQLException {
        dao.update(params);    }
    @Override
    public void delete(String id) throws SQLException {
        dao.delete(id);    }
    @Override
    public boolean isDataExist(String id) throws SQLException {
        return dao.isDataExist(id);    }
    @Override
    public Operasional getLastRecord() throws SQLException {
        return dao.getLastRecord();    }
    @Override
    public String getTotalIncome() throws SQLException {
        return dao.getTotalIncome();    }

    @Override
    public boolean getConnection() { return dao.getConnection(); }
}
