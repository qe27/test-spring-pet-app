package com.qe27.webapp.test.config;

import com.qe27.webapp.service.KafkaMessageProcessor;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class WebTestConfig {
    @Autowired
    protected TestRestTemplate restTemplate;

    @SpyBean
    protected KafkaMessageProcessor kafkaMessageProcessor;
}
