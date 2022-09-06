package com.opera.nexer.urlfeeder.controller;

import com.opera.nexer.urlfeeder.common.Constants;
import com.opera.nexer.urlfeeder.model.URL;
import com.opera.nexer.urlfeeder.service.URLService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1")
public class URLFeeder {

    @Autowired
    private URLService urlService;

    @GetMapping("/")
    public String ping(){
        return "pong";
    }

    @PostMapping("/submit")
    public ResponseEntity<Void> submitUrl(@RequestBody URL url){
        url.setId(Constants.URL_UUID_PREFIX +UUID.randomUUID().toString());
        url.setCreatedDate(new Timestamp(System.currentTimeMillis()));
        urlService.save(url);
        return ResponseEntity.ok().build();
    }

}
