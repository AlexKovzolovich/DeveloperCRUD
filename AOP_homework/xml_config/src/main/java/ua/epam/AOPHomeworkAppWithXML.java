package ua.epam;

import com.zaxxer.hikari.HikariDataSource;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@ImportResource("classpath:app-config.xml")
@PropertySource("classpath:application.properties")
public class AOPHomeworkAppWithXML {

  @Value("${spring.datasource.url}")
  private String dbURL;

  @Value("${spring.datasource.data-username}")
  private String dbUser;

  @Value("${spring.datasource.password}")
  private String dbPassword;

  public static void main(String[] args) {
    SpringApplication.run(AOPHomeworkAppWithXML.class, args);
  }

  @Bean
  public DataSource dataSource() {
    HikariDataSource dataSource = new HikariDataSource();
    dataSource.setJdbcUrl(dbURL);
    dataSource.setUsername(dbUser);
    dataSource.setPassword(dbPassword);

    return dataSource;
  }

}
