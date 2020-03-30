package ua.epam;


import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import ua.epam.BaseSpringIT.TestContextConfiguration;
import ua.epam.util.TaskDtoGenerator;

@RunWith(SpringRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = {HibernateConfiguration.class, TestContextConfiguration.class})
public abstract class BaseSpringIT {

  @Autowired
  protected MockMvc mockMvc;

  @Autowired
  protected RestTemplate restTemplate;

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

    @Bean
    public TaskDtoGenerator generator() {
      return new TaskDtoGenerator();
    }
  }

}
