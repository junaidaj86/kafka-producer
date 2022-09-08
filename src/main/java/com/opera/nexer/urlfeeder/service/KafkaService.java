package com.opera.nexer.urlfeeder.service;

import com.nexer.opera.schema.avro.CloudEventData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;


@Service
public class KafkaService {

    @Autowired
    private KafkaTemplate<String, CloudEventData> kafkaTemplate;


    public void send(String topic, CloudEventData payload){
        kafkaTemplate.send(topic, payload);
    }
}
