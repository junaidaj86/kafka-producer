package com.opera.nexer.urlfeeder.service;

import com.opera.nexer.urlfeeder.controller.URLFeeder;
import com.opera.nexer.urlfeeder.dao.URLRepository;
import com.opera.nexer.urlfeeder.model.URL;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.util.Map;
import java.util.Optional;

@Service
public class URLService {

    Logger LOGGER = LoggerFactory.getLogger(URLService.class);

    @Value("#{${com.opera.nexer.urlfeeder.topics}}")
    Map<String, String> kafkaTopics;

    @Autowired
    private URLRepository urlRepository;

    @Autowired
    private KafkaService kafkaService;

    public void save(URL url){
        try{

            Optional<String> topic = getTopicByPath(url.getUrl());
            if(topic.isEmpty()){
                LOGGER.warn("Content typenot found");
            }else{
                kafkaService.send(topic.get(), url.getUrl());
                urlRepository.save(url);
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
        LOGGER.info("URL: {} has content type: {}", path, rawContectType);
        String contentType = rawContectType.split(";")[0];

        LOGGER.info("key: {} ", contentType);

        if(kafkaTopics.containsKey(contentType)){
            return Optional.of(kafkaTopics.get(contentType));
        }
        return Optional.empty();
    }
}
