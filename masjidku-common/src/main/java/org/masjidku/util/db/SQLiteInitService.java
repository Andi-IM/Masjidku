package org.masjidku.util.db;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SQLiteInitService {
    private static final Logger LOGGER = Logger.getLogger(SQLiteInitService.class.getName());

    /**
     * Executes the SQLite initialization script if the database is SQLite.
     * This will create the necessary tables if they do not exist yet.
     */
    public static void initializeDatabase() {
        DatabaseConnection db = new DatabaseConnection();
        try (Connection conn = db.getConnection()) {
            if (conn == null) {
                LOGGER.severe("Failed to get database connection for initialization.");
                return;
            }
            
            // We only need to run this if it's SQLite (but it won't hurt to run IF NOT EXISTS on MySQL, 
            // though syntax might differ. To be safe, we load the SQLite specific file.)
            InputStream is = SQLiteInitService.class.getResourceAsStream("/org/masjidku/db/masjidku_sqlite.sql");
            if (is == null) {
                LOGGER.warning("SQL schema file masjidku_sqlite.sql not found in resources!");
                return;
            }

            BufferedReader reader = new BufferedReader(new InputStreamReader(is));
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                // Quick and dirty comment removal
                if (line.trim().startsWith("--") || line.trim().startsWith("/*") || line.trim().startsWith("*")) {
                    continue;
                }
                sb.append(line).append("\n");
            }
            
            String[] queries = sb.toString().split(";");
            try (Statement stmt = conn.createStatement()) {
                for (String query : queries) {
                    if (!query.trim().isEmpty()) {
                        stmt.execute(query.trim());
                    }
                }
                LOGGER.info("SQLite database initialized successfully.");
            }
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Error initializing SQLite database", e);
        }
    }
}
