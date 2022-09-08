package com.opera.nexer.urlfeeder.service;

import com.nexer.opera.schema.avro.Event;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;


@Service
public class KafkaService {

    @Autowired
    private KafkaTemplate<String, Event> kafkaTemplate;


    public void send(String topic, String message, Event payload){
        kafkaTemplate.send(topic, payload);
    }
}
