package org.masjidku.accounting.service.impl;
import org.masjidku.accounting.client.service.AnakYatimService;
import org.masjidku.accounting.dao.anakyatim.AnakYatimDao;
import javafx.collections.ObservableList;
import java.sql.SQLException;
import org.masjidku.accounting.client.model.*;
import org.masjidku.accounting.client.model.anakyatim.*;
import org.masjidku.accounting.client.model.operasional.*;
import org.masjidku.accounting.client.model.pembangunan.*;
import org.masjidku.accounting.client.model.tpa.*;
import org.masjidku.accounting.client.model.zakat.*;
public class AnakYatimServiceImpl implements AnakYatimService {
    private final AnakYatimDao dao = new AnakYatimDao();
    @Override
    public AnakYatim get(String id) throws SQLException {
        return dao.get(id);    }
    @Override
    public ObservableList<AnakYatim> getAll() throws SQLException {
        return dao.getAll();    }
    @Override
    public void save(AnakYatim anakYatim) throws SQLException {
        dao.save(anakYatim);    }
    @Override
    public void update(String[] params) throws SQLException {
        dao.update(params);    }
    @Override
    public void delete(String id) throws SQLException {
        dao.delete(id);    }
    @Override
    public AnakYatim getLastRecord() throws SQLException {
        return dao.getLastRecord();    }
    @Override
    public String getTotalOutcome() throws SQLException {
        return dao.getTotalOutcome();    }
    @Override
    public boolean isAnakYatimExist(String id) throws SQLException {
        return dao.isAnakYatimExist(id);    }
    @Override
    public boolean getConnection() { return dao.getConnection(); }
}
