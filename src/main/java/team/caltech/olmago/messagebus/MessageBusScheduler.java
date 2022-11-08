package team.caltech.olmago.messagebus;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import team.caltech.olmago.contract.common.message.MessageEnvelope;

import java.util.List;

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
