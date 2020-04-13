package com.qe27.webapp.service;

import com.qe27.webapp.generated.model.KafkaJsonMessage;
import com.qe27.webapp.generated.model.KafkaStringMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.Map;

@Slf4j
@Service
public class KafkaMessageProcessor {

    private final KafkaTemplate<Long, Map<String, Object>> jsonTemplate;
    private final KafkaTemplate<Long, String> stringTemplate;

    @Autowired
    public KafkaMessageProcessor(KafkaTemplate<Long, Map<String, Object>> jsonTemplate,
                                 KafkaTemplate<Long, String> stringTemplate) {
        this.jsonTemplate = jsonTemplate;
        this.stringTemplate = stringTemplate;
    }

    public void postJsonMessage(KafkaJsonMessage message) {
        log.debug("postJsonMessage {}", message);
        jsonTemplate.send(message.getTopic(), (Map<String, Object>) message.getBody());
    }

    public void postStringMessage(KafkaStringMessage message) {
        log.debug("postStringMessage {}", message);
        stringTemplate.send(message.getTopic(), message.getBody());
    }

    @KafkaListener(id = "map.listener", topics = {"test.app.topic"}, containerFactory = "singleFactory")
    public void consume(Map<String, String> map) {
        log.info("=> map consumed {}", map);
    }

}
