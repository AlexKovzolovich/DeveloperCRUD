package ua.epam.repository.testUtil;

import org.apache.ibatis.jdbc.ScriptRunner;
import ua.epam.util.ConnectionUtil;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

public class TestUtil {
    private static final String PATH_TO_INIT_SCRIPT = "C:\\Users\\Alex\\IdeaProjects\\DeveloperCRUDREST\\src\\test\\resources\\db\\initDB.sql";
    private static final String PATH_TO_POPULATE_SCRIPT = "C:\\Users\\Alex\\IdeaProjects\\DeveloperCRUDREST\\src\\test\\resources\\db\\populateDB.sql";
    private static final String PATH_TO_DROP_SCRIPT = "C:\\Users\\Alex\\IdeaProjects\\DeveloperCRUDREST\\src\\test\\resources\\db\\drop.sql";
    private static Connection connection;

    public static void initTestDB() {
        try (FileReader init = new FileReader(PATH_TO_INIT_SCRIPT)) {
            connection = ConnectionUtil.getConnection();
            ScriptRunner scriptRunner = new ScriptRunner(connection);
            scriptRunner.runScript(init);
        } catch (IOException | SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void populateDB() {
        try (FileReader pop = new FileReader(PATH_TO_POPULATE_SCRIPT)) {
            connection = ConnectionUtil.getConnection();
            ScriptRunner scriptRunner = new ScriptRunner(connection);
            scriptRunner.runScript(pop);
        } catch (IOException | SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void dropDB() {
        try (FileReader drop = new FileReader(PATH_TO_DROP_SCRIPT)) {
            connection = ConnectionUtil.getConnection();
            ScriptRunner scriptRunner = new ScriptRunner(connection);
            scriptRunner.runScript(drop);
        } catch (IOException | SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
