package ua.epam.config;

import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.Map;
import org.apache.log4j.Logger;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import ua.epam.annotation.Timed;

@Configuration
@Import({WebSecurityConfig.class})
@ComponentScan("ua.epam")
@EnableWebMvc
public class AppConfig implements WebMvcConfigurer {

  private static final Logger log = Logger.getLogger(AppConfig.class);

  @Bean
  public BeanPostProcessor TimedAnnotationBeanPostProcessor() {
    return new BeanPostProcessor() {

      Map<String, Class> map = new HashMap<>();

      @Override
      public Object postProcessBeforeInitialization(Object bean, String beanName)
          throws BeansException {
        Class<?> beanClass = bean.getClass();
        if (beanClass.isAnnotationPresent(Timed.class)) {
          map.put(beanName, beanClass);
        }
        return bean;
      }

      @Override
      public Object postProcessAfterInitialization(Object bean, String beanName) {
        Class beanClass = map.get(beanName);
        if (beanClass != null) {
          return Proxy.newProxyInstance(beanClass.getClassLoader(), beanClass.getInterfaces(),
              (proxy, method, args) -> {
                long start = System.nanoTime();
                Object returnValue = method.invoke(bean, args);
                long end = System.nanoTime();
                System.out.println(
                    "Profiling " + beanName + "." + method.getName() + ": " + (end - start)
                        + " ns");
                log.debug("Profiling " + beanName + "." + method.getName() + ": " + (end - start)
                    + " ns");
                return returnValue;
              });
        }
        return bean;
      }
    };
  }

  @Override
  public void addResourceHandlers(ResourceHandlerRegistry registry) {
    registry.addResourceHandler("/WEB-INF/jsp/**").addResourceLocations("/jsp/");
    registry.addResourceHandler("/WEB-INF/img/**").addResourceLocations("/img/");
  }

  @Override
  public void addViewControllers(ViewControllerRegistry registry) {
    registry.addViewController("/").setViewName("index");
    registry.addViewController("/login").setViewName("login");
  }

  @Bean
  public ViewResolver viewResolver() {
    InternalResourceViewResolver resolver = new InternalResourceViewResolver();
    resolver.setPrefix("/WEB-INF/jsp/");
    resolver.setSuffix(".jsp");
    return resolver;
  }

}
