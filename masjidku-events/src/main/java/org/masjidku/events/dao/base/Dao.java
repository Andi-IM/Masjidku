package org.masjidku.events.dao.base;

import org.masjidku.util.db.DatabaseConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public abstract class Dao<T> {

    public Dao() {
        getConnection();
    }
    protected Connection con;
    protected PreparedStatement ps;
    protected ResultSet rs;

    public boolean getConnection() {
        DatabaseConnection connection = new DatabaseConnection();
        if (connection.getConnection() != null) {
            con = connection.getConnection();
            return true;
        }
        return false;
    }
}
