package ua.epam.springBatch.batchPart;

import org.apache.log4j.Logger;
import org.springframework.batch.item.ItemWriter;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import ua.epam.springBatch.model.User;

import java.util.List;

public class UserWriter implements ItemWriter<User> {
    private final Logger logger;
    private JavaMailSender javaMailSender;

    public UserWriter(Logger logger, JavaMailSender javaMailSender) {
        this.logger = logger;
        this.javaMailSender = javaMailSender;
    }

    @Override
    public void write(List<? extends User> list) throws Exception {
        list.forEach(user -> {
            SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
            simpleMailMessage.setTo(user.getAccount().getEmail());
            simpleMailMessage.setSubject("Low balance");
            simpleMailMessage.setText("Dear " + user.getLastName() + ", you receiving this message because your balance is low.");

            javaMailSender.send(simpleMailMessage);

            logger.debug("Message to " + user.getFirstName() + " " + user.getLastName() + ", email address: " + user.getAccount().getEmail());
        });
    }
}
