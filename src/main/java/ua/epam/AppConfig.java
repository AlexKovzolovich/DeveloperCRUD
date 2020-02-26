package ua.epam;

import lombok.extern.log4j.Log4j;
import org.apache.log4j.Logger;
import org.hibernate.jpa.HibernatePersistenceProvider;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import ua.epam.annotation.Timed;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

@Configuration
@EnableWebMvc
@ComponentScan("ua.epam")
@Log4j
public class AppConfig {

    @Resource
    private Environment environment;

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();

        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://fojvtycq53b2f2kx.chr7pe7iynqr.eu-west-1.rds.amazonaws.com/owapv3xvgqrjk3py");
        dataSource.setUsername("ra2qoz0yg0c3b41w");
        dataSource.setPassword("blap8bjsz5kvg4pp");

        return dataSource;
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        LocalContainerEntityManagerFactoryBean localContainerEntityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();

        localContainerEntityManagerFactoryBean.setDataSource(dataSource());
        localContainerEntityManagerFactoryBean.setPersistenceProviderClass(HibernatePersistenceProvider.class);
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

        properties.put("db.hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
        properties.put("db.hibernate.show_sql", true);
        properties.put("db.hibernate.hbm2ddl.auto", "create");

        return properties;
    }

    @Bean
    public BeanPostProcessor TimedAnnotationBeanPostProcessor() {
        return new BeanPostProcessor() {
            Map<String, Class> map = new HashMap<>();
            @Override
            public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
                Class<?> beanClass = bean.getClass();
                if (beanClass.isAnnotationPresent(Timed.class)) {
                    map.put(beanName, beanClass);
                }
                return bean;
            }

            @Override
            public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
                Class beanClass = map.get(beanName);
                if (beanClass != null) {
                    return Proxy.newProxyInstance(beanClass.getClassLoader(), beanClass.getInterfaces(), (proxy, method, args) -> {
                        long start = System.nanoTime();
                        Object returnValue = method.invoke(bean, args);
                        long end = System.nanoTime();
                        System.out.println("Profiling " + beanName + "." + method.getName() + ": " + (end - start) + " ns");
                        log.debug("Profiling " + beanName + "." + method.getName() + ": " + (end - start) + " ns");
                        return returnValue;
                    });
                }
                return bean;
            }
        };
    }
}
