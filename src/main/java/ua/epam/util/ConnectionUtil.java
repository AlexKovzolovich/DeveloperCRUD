package ua.epam.util;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.dbcp2.BasicDataSource;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

@Slf4j
public class ConnectionUtil {
    private static BasicDataSource dataSource = new BasicDataSource();
    private static Properties properties = new Properties();

    static {
        try {
            properties.load(new BufferedReader(new FileReader("db/dataSource.properties")));
            Class.forName(properties.getProperty("database.driver"));
        } catch (IOException | ClassNotFoundException e) {
            log.error("", e);
        }
        dataSource.setUrl(properties.getProperty("database.url"));
        dataSource.setUsername(properties.getProperty("database.user"));
        dataSource.setPassword(properties.getProperty("database.password"));
        dataSource.setMinIdle(Integer.parseInt(properties.getProperty("database.minIdle")));
        dataSource.setMaxIdle(Integer.parseInt(properties.getProperty("database.maxIdle")));
        dataSource.setMaxOpenPreparedStatements(Integer.parseInt(properties.getProperty("database.maxOpenedConnectionPool")));
    }

    public static Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }
}
