package ua.epam.config;

import java.util.Properties;
import javax.sql.DataSource;
import org.hibernate.jpa.HibernatePersistenceProvider;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;

@Configuration
@PropertySource("classpath:db/dataSource.properties")
public class PersistentConfig {

  @Value("${database.driver}")
  private String jdbcDriverClass;

  @Value("${database.url}")
  private String databaseUrl;

  @Value("${database.user}")
  private String databaseUser;

  @Value("${database.password}")
  private String databasePassword;

  @Bean
  public DataSource dataSource() {
    DriverManagerDataSource dataSource = new DriverManagerDataSource();

    dataSource.setDriverClassName(jdbcDriverClass);
    dataSource.setUrl(databaseUrl);
    dataSource.setUsername(databaseUser);
    dataSource.setPassword(databasePassword);

    return dataSource;
  }

  @Bean
  public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
    LocalContainerEntityManagerFactoryBean localContainerEntityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();

    localContainerEntityManagerFactoryBean.setDataSource(dataSource());
    localContainerEntityManagerFactoryBean
        .setPersistenceProviderClass(HibernatePersistenceProvider.class);
    localContainerEntityManagerFactoryBean.setPackagesToScan("ua.epam.model");
    localContainerEntityManagerFactoryBean.setJpaProperties(getHibernateProperties());

    return localContainerEntityManagerFactoryBean;
  }

  @Bean
  public JpaTransactionManager transactionManager() {
    JpaTransactionManager transactionManager = new JpaTransactionManager();
    transactionManager.setEntityManagerFactory(entityManagerFactory().getObject());

    return transactionManager;
  }

  private Properties getHibernateProperties() {
    Properties properties = new Properties();

    properties.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQL5Dialect");
    properties.setProperty("hibernate.show_sql", "true");
    properties.setProperty("hibernate.hbm2ddl.auto", "none");
    properties.setProperty("hibernate.current_session_context_class",
        "org.springframework.orm.hibernate5.SpringSessionContext");

    return properties;
  }
}
