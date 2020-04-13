package com.qe27.webapp.controller;

import com.qe27.webapp.generated.api.KafkaEventApi;
import com.qe27.webapp.generated.model.KafkaUnifiedMessageModel;
import com.qe27.webapp.service.KafkaMessageProcessor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Slf4j
@RestController
public class KafkaEventController implements KafkaEventApi {

    private final KafkaMessageProcessor kafkaMessageProcessor;

    @Autowired
    public KafkaEventController(KafkaMessageProcessor kafkaMessageProcessor) {
        this.kafkaMessageProcessor = kafkaMessageProcessor;
    }

    @Override
    public ResponseEntity<Void> createEvent(@Valid KafkaUnifiedMessageModel kafkaUnifiedMessageModel) {
        log.debug("create message called with body {}", kafkaUnifiedMessageModel);
        if (kafkaUnifiedMessageModel.getJsonMessage() != null) {
            kafkaMessageProcessor.postJsonMessage(kafkaUnifiedMessageModel.getJsonMessage());
        } else if (kafkaUnifiedMessageModel.getStringMessage() != null) {
            kafkaMessageProcessor.postStringMessage(kafkaUnifiedMessageModel.getStringMessage());
        }
        return null;
    }
}