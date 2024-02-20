package ru.t1academy.subscriber;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import ru.t1academy.messageBroker.annotation.Subscriber;
import ru.t1academy.messageBroker.queue.MessageQueue;
import ru.t1academy.model.SupportPhrase;
import ru.t1academy.service.SupportService;

import java.io.IOException;

@Slf4j
@Component
@RequiredArgsConstructor
public class MessageSubscriberImpl implements MessageSubscriber {
    private final MessageQueue<SupportPhrase> queue;
    private final SupportService supportService;

    @Subscriber
    @Override
    public SupportPhrase subscribe() {
        SupportPhrase supportPhrase = queue.poll();
        try {
            supportService.addSupportPhrase(supportPhrase);
            log.info("Subscribing is started!");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return supportPhrase;
    }

}
