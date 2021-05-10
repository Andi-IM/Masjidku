package org.masjidku;

import org.junit.Assert;
import org.junit.Test;
import org.masjidku.util.DatabaseConnection;

public class DbTest {
    DatabaseConnection connection = new DatabaseConnection();

    @Test
    public void databaseConnectionTest(){
        // calling connection in dbconnection
        Assert.assertNotNull("Periksa koneksi database!", "");
    }
}
