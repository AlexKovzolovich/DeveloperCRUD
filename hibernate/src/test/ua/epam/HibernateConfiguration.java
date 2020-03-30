package ua.epam;

import java.util.Properties;
import javax.sql.DataSource;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.test.context.TestPropertySource;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@TestPropertySource("classpath:test.properties")
public class HibernateConfiguration {

  @Bean
  public PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
    return new PropertySourcesPlaceholderConfigurer();
  }

  @Bean
  @Primary
  public DataSource dataSource(
      @Value("${spring.datasource.driver-class-name:org.h2.Driver}") String driver,
      @Value("${spring.datasource.url:jdbc:h2:mem:db;INIT=RUNSCRIPT FROM 'classpath:resources/init_db.sql';INIT=RUNSCRIPT FROM 'classpath:resources/populate_db.sql';DB_CLOSE_DELAY=-1}") String url,
      @Value("${spring.datasource.username:sa}") String user,
      @Value("${spring.datasource.password:sa}") String password) {

    DriverManagerDataSource dataSource = new DriverManagerDataSource();
    dataSource.setDriverClassName(driver);
    dataSource.setUrl(url);
    dataSource.setUsername(user);
    dataSource.setPassword(password);

    return dataSource;
  }

  @Bean
  @Primary
  public LocalSessionFactoryBean sessionFactoryBean(
      @Value("${spring.jpa.packageToScan:ua.epam.model}") String packageToScan,
      @Value("${spring.jpa.hibernate.dialect:org.hibernate.dialect.H2Dialect}") String dialect,
      @Value("${spring.jpa.hibernate.ddl-auto:none}") String ddlMode,
      @Value("${spring.jpa.hibernate.show_sql:true}") String showSql,
      @Value("${spring.jpa.hibernate.format_sql:true}") String formatSql,
      @Autowired DataSource dataSource) {

    LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
    sessionFactory.setDataSource(dataSource);
    sessionFactory.setPackagesToScan(packageToScan);
    sessionFactory
        .setHibernateProperties(hibernateProperties(dialect, ddlMode, showSql, formatSql));

    return sessionFactory;
  }

  @Bean
  @Primary
  public PlatformTransactionManager hibernateTransactionManager(SessionFactory sessionFactory) {
    HibernateTransactionManager transactionManager = new HibernateTransactionManager();
    transactionManager.setSessionFactory(sessionFactory);
    return transactionManager;
  }


  private Properties hibernateProperties(String dialect, String ddlMode,
      String showSql, String formatSql) {

    Properties properties = new Properties();
    properties.setProperty("hibernate.dialect", dialect);
    properties.setProperty("hibernate.hbm2ddl.auto", ddlMode);
    properties.setProperty("hibernate.show_sql", showSql);
    properties.setProperty("hibernate.format_sql", formatSql);

    return properties;
  }
}
