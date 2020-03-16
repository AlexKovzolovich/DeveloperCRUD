package ua.epam.springBatch;

import com.zaxxer.hikari.HikariDataSource;
import java.math.BigDecimal;
import java.util.Properties;
import javax.sql.DataSource;
import org.apache.log4j.Logger;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.JdbcCursorItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import ua.epam.springBatch.batchPart.UserBalanceFilter;
import ua.epam.springBatch.batchPart.UserWriter;
import ua.epam.springBatch.model.Account;
import ua.epam.springBatch.model.User;
import ua.epam.springintegrationhomework.SpringIntegrationHomeworkApplication;

@SpringBootApplication
@EnableBatchProcessing
public class SpringBatchHomework {

  private static final String sqlQuery =
      "SELECT user.id, user.first_name, user.last_name, user.account_id, account.balance, account.email "
          +
          "FROM user JOIN account ON user.account_id = account.id";

  @Autowired
  private JobBuilderFactory jobBuilderFactory;

  @Autowired
  private StepBuilderFactory stepBuilderFactory;

  public static void main(String[] args) {
    SpringApplication.run(SpringIntegrationHomeworkApplication.class, args);
  }

  @Bean
  public ItemReader<User> itemReader(DataSource dataSource, RowMapper<User> rowMapper) {
    JdbcCursorItemReader<User> itemReader = new JdbcCursorItemReader<>();
    itemReader.setDataSource(dataSource);
    itemReader.setSql(sqlQuery);
    itemReader.setRowMapper(rowMapper);

    return itemReader;
  }

  @Bean
  public RowMapper<User> rowMapper() {
    return (resultSet, rowNum) -> {
      Long userId = resultSet.getLong("user.id");
      String firstName = resultSet.getString("user.first_name");
      String lastName = resultSet.getString("user.last_name");
      Long accountId = resultSet.getLong("user.account_id");
      BigDecimal balance = resultSet.getBigDecimal("account.balance");
      String email = resultSet.getString("account.email");

      Account account = new Account(accountId, balance, email);

      return new User(userId, firstName, lastName, account);
    };
  }

  @Bean
  public DataSource dataSource() {
    HikariDataSource dataSource = new HikariDataSource();
    dataSource.setJdbcUrl(
        "jdbc:mysql://localhost:3306?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC");
    dataSource.setUsername("root");
    dataSource.setPassword("root");

    return dataSource;
  }

  @Bean
  public Logger logger() {
    return Logger.getLogger(SpringBatchHomework.class);
  }

  @Bean
  public JavaMailSender javaMailSender() {
    JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
    mailSender.setHost("smtp.gmail.com");
    mailSender.setPort(587);

    mailSender.setUsername("my.gmail@gmail.com");
    mailSender.setPassword("password");

    Properties props = mailSender.getJavaMailProperties();
    props.put("mail.transport.protocol", "smtp");
    props.put("mail.smtp.auth", "true");
    props.put("mail.smtp.starttls.enable", "true");
    props.put("mail.debug", "true");

    return mailSender;
  }

  @Bean
  public ItemWriter<User> itemWriter() {
    return new UserWriter(logger(), javaMailSender());
  }

  @Bean
  public ItemProcessor<User, User> itemProcessor() {
    return new UserBalanceFilter();
  }

  @Bean
  public Job userJob() {
    return jobBuilderFactory.get("userJob")
        .incrementer(new RunIdIncrementer())
        .flow(step())
        .end()
        .build();
  }

  @Bean
  public Step step() {
    return stepBuilderFactory.get("step")
        .<User, User>chunk(10)
        .reader(itemReader(dataSource(), rowMapper()))
        .processor(itemProcessor())
        .writer(itemWriter())
        .build();
  }
}
