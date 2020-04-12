package com.epam.messaginghomework;

import static com.epam.messaginghomework.ActiveMQConfig.REQUEST_CHANNEL;
import static com.epam.messaginghomework.ActiveMQConfig.RESPONSE_CHANNEL;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class MessageReceiver {

  private int counter = 0;

  private final Map<UUID, RequestMessage> messages = new HashMap<>();

  private JmsTemplate jmsTemplate;

  @Autowired
  public MessageReceiver(JmsTemplate jmsTemplate) {
    this.jmsTemplate = jmsTemplate;
  }

  @JmsListener(destination = REQUEST_CHANNEL)
  public void receiveMessage(@Payload RequestMessage requestMessage) {
    if (messages.put(requestMessage.getId(), requestMessage) == null) {
      counter++;
      if (counter >= 10) {
        sendResponse();
        counter = 0;
        messages.clear();
      }
    }
  }

  public void sendResponse() {
    messages.forEach(
        (id, message) -> jmsTemplate.convertAndSend(RESPONSE_CHANNEL,
            new ResponseMessage(id,
                message.getFirstAddendum().add(message.getSecondAddendum()))));
  }
}
