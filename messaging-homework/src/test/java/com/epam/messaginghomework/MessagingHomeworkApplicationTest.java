package com.epam.messaginghomework;

import static org.junit.Assert.*;

import java.util.Map;
import java.util.Map.Entry;
import java.util.UUID;
import java.util.function.Function;
import java.util.stream.Collectors;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
class MessagingHomeworkApplicationTest {

	@Autowired
	private MessageSender sender;

	@Test
	public void testApplication() throws InterruptedException {

		Thread.sleep(1000);

		Map<UUID, RequestMessage> requestMessageMap = sender.getSent().stream()
				.collect(Collectors.toMap(RequestMessage::getId, Function.identity()));

		Map<UUID, ResponseMessage> responseMessageMap = sender.getReceived().stream()
				.collect(Collectors.toMap(ResponseMessage::getId, Function.identity()));

		for (Entry<UUID, RequestMessage> entry : requestMessageMap.entrySet()) {
			RequestMessage requestMessage = entry.getValue();
			ResponseMessage responseMessage = responseMessageMap.get(entry.getKey());

			assertEquals(requestMessage.getFirstAddendum().add(requestMessage.getSecondAddendum()),
					responseMessage.getSum());
		}
	}
}
