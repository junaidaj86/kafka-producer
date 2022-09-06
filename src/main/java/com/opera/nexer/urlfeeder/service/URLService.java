package com.opera.nexer.urlfeeder.service;

import com.opera.nexer.urlfeeder.dao.URLRepository;
import com.opera.nexer.urlfeeder.model.URL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class URLService {

    @Autowired
    private URLRepository urlRepository;

    @Autowired
    private KafkaService kafkaService;

    public void save(URL url){
        urlRepository.save(url);
        kafkaService.send("html_topic", url.getUrl());
    }
}
