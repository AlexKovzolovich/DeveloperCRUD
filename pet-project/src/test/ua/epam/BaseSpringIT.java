package ua.epam;


import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

import javax.sql.DataSource;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@RunWith(SpringRunner.class)
@WebAppConfiguration
@ContextConfiguration
@PropertySource("classpath:resources/test.properties")
public abstract class BaseSpringIT {

  @Autowired
  protected MockMvc mockMvc;

  @Autowired
  protected RestTemplate restTemplate;

  @Bean
  public DataSource dataSource(@Value("spring.datasource.driver-class-name") String driver,
      @Value("spring.datasource.url") String url,
      @Value("spring.datasource.username") String user,
      @Value("spring.datasource.password") String password) {
    DriverManagerDataSource dataSource = new DriverManagerDataSource();
    dataSource.setDriverClassName(driver);
    dataSource.setUrl(url);
    dataSource.setUsername(user);
    dataSource.setPassword(password);

    return dataSource;
  }

  @Configuration
  @EnableWebMvc
  @ComponentScan("ua.epam")
  protected static class TestContextConfiguration {

    @Bean
    public MockMvc mockMvc(@Autowired WebApplicationContext webApplicationContext) {
      return MockMvcBuilders
          .webAppContextSetup(webApplicationContext)
          .apply(SecurityMockMvcConfigurers.springSecurity())
          .alwaysDo(print())
          .build();
    }

    @Bean
    public RestTemplate restTemplate() {
      return Mockito.mock(RestTemplate.class);
    }
  }

}
