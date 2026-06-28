package org.masjidku.accounting.service.impl;
import org.masjidku.accounting.client.service.DonasiOperationalService;
import org.masjidku.accounting.dao.operasional.DonasiOperationalDao;
import javafx.collections.ObservableList;
import java.sql.SQLException;
import org.masjidku.accounting.client.model.*;
import org.masjidku.accounting.client.model.anakyatim.*;
import org.masjidku.accounting.client.model.operasional.*;
import org.masjidku.accounting.client.model.pembangunan.*;
import org.masjidku.accounting.client.model.tpa.*;
import org.masjidku.accounting.client.model.zakat.*;
public class DonasiOperationalServiceImpl implements DonasiOperationalService {
    private final DonasiOperationalDao dao = new DonasiOperationalDao();
    @Override
    public DonasiOperasional get(String id) throws SQLException {
        return dao.get(id);    }
    @Override
    public ObservableList<DonasiOperasional> getAll() throws SQLException {
        return dao.getAll();    }
    @Override
    public void save(DonasiOperasional donasiOperasional) throws SQLException {
        dao.save(donasiOperasional);    }
    @Override
    public void update(String[] params) throws SQLException {
        dao.update(params);    }
    @Override
    public void delete(String id) throws SQLException {
        dao.delete(id);    }
    @Override
    public boolean isDonaturExist(String id) throws SQLException {
        return dao.isDonaturExist(id);    }
    @Override
    public DonasiOperasional getLastRecord() throws SQLException {
        return dao.getLastRecord();    }
    @Override
    public String getTotalOutcome() throws SQLException {
        return dao.getTotalOutcome();    }
    @Override
    public boolean getConnection() { return dao.getConnection(); }
}
