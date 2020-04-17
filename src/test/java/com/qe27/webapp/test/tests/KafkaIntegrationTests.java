package com.qe27.webapp.test.tests;

import com.qe27.webapp.test.config.KafkaTestConfig;
import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.test.utils.KafkaTestUtils;

import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.timeout;
import static org.mockito.Mockito.verify;

public class KafkaIntegrationTests extends KafkaTestConfig {

    @Value("classpath:create_kafka_event_body.json")
    Resource createKafkaEventBodyJson;

    @Test
    public void testKafkaProducer() throws IOException {
        String testTopicName = "test.app.topic";
        Map<String, Object> consumerConfig = new HashMap<>(KafkaTestUtils.consumerProps("consumer",
                "false", embeddedKafkaBroker));
        Consumer<String, String> testConsumer = new DefaultKafkaConsumerFactory<>(consumerConfig,
                new StringDeserializer(), new StringDeserializer()).createConsumer();
        testConsumer.subscribe(Collections.singleton(testTopicName));

        new Thread(() -> {
            ConsumerRecord<String, String> singleRecord = KafkaTestUtils.getSingleRecord(testConsumer, testTopicName, 3000L);
            assertThat(singleRecord).isNotNull();
            assertThat(singleRecord.key()).isEqualTo("my-aggregate-id");
            assertThat(singleRecord.value()).isEqualTo("my-test-value");
        }).start();
        restTemplate.postForEntity("/kafkaEvent", createKafkaEventBodyJson, String.class);
    }

    @Test
    public void testKafkaConsumer() {
        restTemplate.postForEntity("/kafkaEvent", createKafkaEventBodyJson, String.class);
        verify(kafkaMessageProcessor, timeout(3000L)).consume(any());
    }

}
