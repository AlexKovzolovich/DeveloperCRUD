package com.epam.messaginghomework;

import static com.epam.messaginghomework.ActiveMQConfig.REQUEST_CHANNEL;
import static com.epam.messaginghomework.ActiveMQConfig.RESPONSE_CHANNEL;

import java.util.ArrayList;
import java.util.Collection;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class MessageSender {

  private static final Logger LOGGER = LoggerFactory.getLogger(MessageSender.class);

  private final List<RequestMessage> sent = new ArrayList<>();

  private final List<ResponseMessage> received = new ArrayList<>();

  private JmsTemplate jmsTemplate;

  @Autowired
  public MessageSender(JmsTemplate jmsTemplate) {
    this.jmsTemplate = jmsTemplate;
  }

  @JmsListener(destination = RESPONSE_CHANNEL)
  public void receive(@Payload ResponseMessage responseMessage) {
    received.add(responseMessage);
    LOGGER.info("Response received: id = " + responseMessage.getId() + ", sum = " + responseMessage.getSum());
  }

  private void send(RequestMessage requestMessage) {
    LOGGER.info("Request sent: id = " + requestMessage.getId() + "; "
        + requestMessage.getFirstAddendum() + " + " + requestMessage.getSecondAddendum());
    jmsTemplate.convertAndSend(REQUEST_CHANNEL, requestMessage);
    sent.add(requestMessage);
  }

  public void send(Collection<RequestMessage> requestMessages) {
    requestMessages.forEach(this::send);

  }

  public List<RequestMessage> getSent() {
    return sent;
  }

  public List<ResponseMessage> getReceived() {
    return received;
  }
}
