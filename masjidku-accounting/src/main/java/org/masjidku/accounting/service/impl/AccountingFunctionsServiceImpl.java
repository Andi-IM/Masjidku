package org.masjidku.accounting.service.impl;

import org.masjidku.accounting.client.service.AccountingFunctionsService;
import org.masjidku.accounting.domain.repository.base.HibernateUtil;

import java.sql.SQLException;

public class AccountingFunctionsServiceImpl implements AccountingFunctionsService {

    private String getBalance(String query) {
        try {
            var session = HibernateUtil.getSessionFactory().getCurrentSession();
            var count = session.createNativeQuery(query, Double.class).uniqueResult();
            return count != null ? String.valueOf(count.longValue()) : "0";
        } catch (Exception e) {
            e.printStackTrace();
            return "0";
        }
    }

    @Override
    public String getInfakYatimBalance() throws SQLException {
        return getBalance("SELECT (SELECT COALESCE(SUM(jumlah), 0) FROM infak_anakyatim) - (SELECT COALESCE(SUM(jumlah), 0) FROM penerima_anakyatim)");
    }

    @Override
    public String getOperationalBalance() throws SQLException {
        return getBalance("SELECT (SELECT COALESCE(SUM(jumlah), 0) FROM infak_operasional) - (SELECT COALESCE(SUM(jumlah), 0) FROM operasional_keluar)");
    }

    @Override
    public String getPembangunanBalance() throws SQLException {
        return getBalance("SELECT (SELECT COALESCE(SUM(jumlah), 0) FROM infak_pembangunan) - (SELECT COALESCE(SUM(jumlah), 0) FROM pembangunan_keluar)");
    }

    @Override
    public String getTpaBalance() throws SQLException {
        return getBalance("SELECT (SELECT COALESCE(SUM(jumlah), 0) FROM infak_tpa) - (SELECT COALESCE(SUM(jumlah), 0) FROM tpa_keluar)");
    }

    @Override
    public String getZakatBalance() throws SQLException {
        return getBalance("SELECT (SELECT COALESCE(SUM(jumlah), 0) FROM pemberi_zakat) - (SELECT COALESCE(SUM(jumlah), 0) FROM penerima_zakat)");
    }

    @Override
    public boolean getConnection() {
        return true;
    }
}
