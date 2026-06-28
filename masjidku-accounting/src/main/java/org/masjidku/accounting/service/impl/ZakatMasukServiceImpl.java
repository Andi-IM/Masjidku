package org.masjidku.accounting.service.impl;
import org.masjidku.accounting.client.service.ZakatMasukService;
import org.masjidku.accounting.dao.zakat.ZakatMasukDao;
import javafx.collections.ObservableList;
import java.sql.SQLException;
import org.masjidku.accounting.client.model.*;
import org.masjidku.accounting.client.model.anakyatim.*;
import org.masjidku.accounting.client.model.operasional.*;
import org.masjidku.accounting.client.model.pembangunan.*;
import org.masjidku.accounting.client.model.tpa.*;
import org.masjidku.accounting.client.model.zakat.*;
public class ZakatMasukServiceImpl implements ZakatMasukService {
    private final ZakatMasukDao dao = new ZakatMasukDao();
    @Override
    public ZakatMasuk get(String id) throws SQLException {
        return dao.get(id);    }
    @Override
    public ObservableList<ZakatMasuk> getAll() throws SQLException {
        return dao.getAll();    }
    @Override
    public void save(ZakatMasuk zakatMasuk) throws SQLException {
        dao.save(zakatMasuk);    }
    @Override
    public void update(String[] params) throws SQLException {
        dao.update(params);    }
    @Override
    public void delete(String id) throws SQLException {
        dao.delete(id);    }
    @Override
    public ZakatMasuk getLastRecord() throws SQLException {
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
