package com.epam.messaginghomework;


import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MessagingHomeworkApplication implements ApplicationRunner {

	private final BigDecimal start = new BigDecimal("0.1");

	private MessageSender messageSender;

	@Autowired
	public MessagingHomeworkApplication(MessageSender messageSender) {
		this.messageSender = messageSender;
	}

	public static void main(String[] args) {
		SpringApplication.run(MessagingHomeworkApplication.class, args);
	}

	@Override
	public void run(ApplicationArguments args) throws Exception {
		List<RequestMessage> messages = IntStream.range(0, 10)
				.mapToObj((i) -> new RequestMessage(start.multiply(new BigDecimal(i)),
						start.multiply(new BigDecimal(i + 1))))
				.collect(Collectors.toList());
		messageSender.send(messages);
	}
}
