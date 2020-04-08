package com.qe27.webapp.controller;

import lombok.extern.slf4j.Slf4j;
import org.openapitools.api.KafkaEventApi;
import org.openapitools.model.KafkaEvent;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@RestController
public class KafkaEventController implements KafkaEventApi {
    @Override
    public ResponseEntity<Void> createEvent(@Valid KafkaEvent kafkaEvent) {
        log.debug("create event called with body {}", kafkaEvent);
        return null;
    }

    @Override
    public ResponseEntity<List<KafkaEvent>> getEvents() {
        log.debug("get events called");
        return null;
    }
}