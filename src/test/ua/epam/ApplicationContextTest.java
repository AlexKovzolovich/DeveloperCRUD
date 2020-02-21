package ua.epam;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ua.epam.model.Account;
import ua.epam.service.AccountService;

import static org.junit.Assert.*;

public class ApplicationContextTest {
    @Test
    public void testContext() {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext("ua.epam");
        AccountService accountService = applicationContext.getBean("accountService", AccountService.class);
        Account account = accountService.getById(1L);
        System.out.println(account);
        assertNotNull(account);
    }
}
