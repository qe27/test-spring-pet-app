package com.qe27.webapp.test.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.test.EmbeddedKafkaBroker;
import org.springframework.kafka.test.context.EmbeddedKafka;
import org.springframework.test.context.TestPropertySource;

@EmbeddedKafka(partitions = 1,
        topics = { "test.app.topic" },
        brokerProperties = {
        "listeners=PLAINTEXT://localhost:19092",
        "port=19092"
})
@TestPropertySource(properties = "kafka.server=127.0.0.1:19092")
public class KafkaTestConfig extends WebTestConfig {
    @Autowired
    protected EmbeddedKafkaBroker embeddedKafkaBroker;
}
