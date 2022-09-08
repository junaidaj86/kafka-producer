package com.opera.nexer.urlfeeder.controller;

import com.nexer.opera.schema.avro.CloudEventData;
import com.opera.nexer.urlfeeder.model.CloudEventsEntity;
import com.opera.nexer.urlfeeder.service.URLService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;


@RestController
@RequestMapping("/api/v1")
public class URLFeeder {

    Logger LOGGER = LoggerFactory.getLogger(URLFeeder.class);

    @Autowired
    private URLService urlService;

    @GetMapping("/")
    public String ping(){
        return "pong";
    }

    @PostMapping("/submit")
    public ResponseEntity<Void> submitUrl(@RequestBody CloudEventData payload){
        LOGGER.info("CloudEventsEntity received" + payload);
        urlService.save(payload);
        return ResponseEntity.ok().build();
    }

}
