package com.opera.nexer.urlfeeder.service;

import com.nexer.opera.schema.avro.CloudEventData;
import com.opera.nexer.urlfeeder.dao.URLRepository;
import com.opera.nexer.urlfeeder.model.CloudEventsEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@Service
public class URLService {

    Logger LOGGER = LoggerFactory.getLogger(URLService.class);

    @Value("#{${com.opera.nexer.urlfeeder.topics}}")
    Map<String, String> kafkaTopics;

    @Autowired
    private URLRepository urlRepository;

    @Autowired
    private KafkaService kafkaService;

    public void save(CloudEventData payload){
        try{
            LOGGER.info("=================================================================================");


            payload.setID("1234");
            LOGGER.info("data="+ payload);
            LOGGER.info("======================================3===========================================");
            Optional<String> topic = getTopicByPath((String) payload.getUrl());
            if(topic.isEmpty()){
                LOGGER.warn("Content type not found");
            }else{

                CloudEventsEntity cloudEventsEntity = new CloudEventsEntity();
                cloudEventsEntity.setUrl(String.valueOf(payload.getUrl()));
                cloudEventsEntity.setId(String.valueOf(UUID.randomUUID()));
                cloudEventsEntity.setName(String.valueOf(payload.getName()));
                cloudEventsEntity.setDescription(String.valueOf(payload.getDescription()));
                LOGGER.info("================================2================================================="+ topic.get());
                kafkaService.send(topic.get(), payload);
                urlRepository.save(cloudEventsEntity);
            }

        }catch (IOException e){
            LOGGER.error("error while sending data");
        }

    }

    private Optional<String> getTopicByPath(String path) throws IOException {
        java.net.URL url = new java.net.URL(path);
        HttpURLConnection urlConnection = (HttpURLConnection)url.openConnection();
        urlConnection.setRequestMethod("HEAD");
        urlConnection.connect();
        String rawContectType =  urlConnection.getContentType();
        LOGGER.info("CloudEventsEntity: {} has content type: {}", path, rawContectType);
        String contentType = rawContectType.split(";")[0];

        LOGGER.info("key: {} ", contentType);

        if(kafkaTopics.containsKey(contentType)){
            return Optional.of(kafkaTopics.get(contentType));
        }
        return Optional.empty();
    }
}
