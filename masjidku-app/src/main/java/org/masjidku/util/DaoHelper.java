package org.masjidku.util;

import javafx.stage.Stage;
import org.slf4j.Logger;
import java.sql.SQLException;

public class DaoHelper {

    public interface CheckAction {
        boolean check() throws SQLException;
    }

    public interface ExecuteAction {
        void execute() throws SQLException;
    }

    public static void saveOrUpdate(CheckAction checkAction, ExecuteAction updateAction, ExecuteAction saveAction, Stage dialogStage, Logger log) {
        try {
            if (checkAction.check()) {
                updateAction.execute();
                AlertHelper.alertInfo(dialogStage, "Success", "Data telah diupdate");
            } else {
                saveAction.execute();
                AlertHelper.alertInfo(dialogStage, "Success", "Data berhasil disimpan");
            }
        } catch (SQLException e) {
            log.error("An error occurred", e);
        }
    }
}
