package ua.epam.springintegrationhomework;

import com.zaxxer.hikari.HikariDataSource;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.DependsOn;
import org.springframework.integration.annotation.Gateway;
import org.springframework.integration.annotation.MessagingGateway;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.Pollers;
import org.springframework.integration.scheduling.PollerMetadata;
import ua.epam.springintegrationhomework.model.DeliveryType;
import ua.epam.springintegrationhomework.model.Package;
import ua.epam.springintegrationhomework.util.PackageGenerator;

import javax.sql.DataSource;
import java.io.IOException;
import java.util.concurrent.Executors;

@SpringBootApplication
public class SpringIntegrationHomeworkApplication {

    public static void main(String[] args) throws IOException {
        ConfigurableApplicationContext context = SpringApplication.run(SpringIntegrationHomeworkApplication.class, args);
        DeliveryService deliveryService = context.getBean(DeliveryService.class);

        for (int i = 0; i < 20; i++) {
            Package pack = PackageGenerator.generatePackage();
            deliveryService.acceptPackage(pack);
        }

        System.out.println("Hit 'Enter' to terminate");
        System.in.read();
        context.close();
    }

    @MessagingGateway
    public interface DeliveryService {
        @Gateway(requestChannel = "packageFlow.input")
        void acceptPackage(Package pack);
    }

    @Bean(name = PollerMetadata.DEFAULT_POLLER)
    public PollerMetadata poller() {
        return Pollers.fixedDelay(1000).get();
    }

    @Bean
    @DependsOn({"logger"})
    public IntegrationFlow packageFlow(@Qualifier("logger") Logger logger) {
        return f -> f
                .channel(c -> c.executor(Executors.newCachedThreadPool()))
                .<Package, DeliveryType>route(Package::getDeliveryType, mapping -> mapping
                        .subFlowMapping(DeliveryType.DTH, sf -> sf
                                .channel(c -> c.queue(10))
                                .handle(m -> logger.info("Delivered to home: " + m.getPayload())))
                        .subFlowMapping(DeliveryType.DTS, sf -> sf
                                .channel(c -> c.queue(10))
                                .handle(m -> logger.info("Delivered to store: " + m.getPayload())))
                        .subFlowMapping(DeliveryType.TRANSFER, sf -> sf
                                .channel(c -> c.queue(10))
                                .handle(m -> logger.info("Left in the delivery service: " + m.getPayload()))));
    }

    @Bean
    public DataSource dataSource() {
        HikariDataSource dataSource = new HikariDataSource();
        dataSource.setJdbcUrl("jdbc:mysql://localhost:3306?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC");
        dataSource.setUsername("root");
        dataSource.setPassword("root");

        return dataSource;
    }

    @Bean
    public Logger logger() {
        return Logger.getLogger(SpringIntegrationHomeworkApplication.class);
    }

}
