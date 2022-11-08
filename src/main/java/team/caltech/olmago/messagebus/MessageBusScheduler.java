package team.caltech.olmago.messagebus;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import team.caltech.olmago.common.message.MessageEnvelope;

import java.util.List;

@Slf4j
@Component
public class MessageBusScheduler {
  private final MessageBus messageBus;
  
  @Autowired
  public MessageBusScheduler(MessageBus messageBus) {
    this.messageBus = messageBus;
  }
  
  @Scheduled(fixedDelay = 5000)
  public void fetchMessagesAndSend() {
    List<MessageEnvelope> msgs = messageBus.findNotSentMessageEnvelopes();
    msgs.forEach(messageBus::sendMessage);
    log.info(msgs.size() + " processed!");
  }
  
}
