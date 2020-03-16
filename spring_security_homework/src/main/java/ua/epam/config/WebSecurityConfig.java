package ua.epam.config;

import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@Import({PersistentConfig.class})
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

  @Autowired
  private DataSource dataSource;

  @Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    auth.jdbcAuthentication()
        .dataSource(dataSource)
        .usersByUsernameQuery("SELECT user_name, password, enabled FROM users WHERE user_name=?")
        .authoritiesByUsernameQuery(
            "SELECT user_name, authority FROM user_roles r JOIN users u ON r.user_id=u.user_id WHERE user_name=?");
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http
        .authorizeRequests()
        .antMatchers("/secret/**")
        .permitAll()
        .anyRequest()
        .authenticated()
        .and()
        .formLogin();

    /*http
        .formLogin()
        .loginPage("/login")
        .loginProcessingUrl("/j_spring_security_check")
        .failureForwardUrl("/login?error")
        .usernameParameter("j_username")
        .passwordParameter("j_password")
        .permitAll();*/
  }
}
