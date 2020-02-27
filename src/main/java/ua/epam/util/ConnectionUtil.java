package ua.epam.util;

import lombok.extern.log4j.Log4j;
import org.apache.commons.dbcp2.BasicDataSource;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

@Log4j
public class ConnectionUtil {
    private final static BasicDataSource dataSource = new BasicDataSource();
    private final static Properties properties = new Properties();
    private static String databaseProperties = "";
    private static volatile boolean isInitialized = false;

    public static void changeToTestMod() {
        databaseProperties = "./src/test/resources/db/dataSource.properties";
    }

    public static synchronized void init() {
        if (isInitialized) {
            return;
        }
        try {
            properties.load(new BufferedReader(new FileReader(databaseProperties)));
        } catch (IOException e) {
            log.error("", e);
        }
        /*dataSource.setDriverClassName(properties.getProperty("database.driver"));
        dataSource.setUrl(properties.getProperty("database.url"));
        dataSource.setUsername(properties.getProperty("database.user"));
        dataSource.setPassword(properties.getProperty("database.password"));
        isInitialized = true;*/
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://fojvtycq53b2f2kx.chr7pe7iynqr.eu-west-1.rds.amazonaws.com/owapv3xvgqrjk3py");
        dataSource.setUsername("ra2qoz0yg0c3b41w");
        dataSource.setPassword("blap8bjsz5kvg4pp");
        isInitialized = true;
    }

    public static Connection getConnection() throws SQLException {
        if (!isInitialized) {
            init();
        }
        return dataSource.getConnection();
    }
}
