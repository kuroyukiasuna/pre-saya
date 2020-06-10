package com.pre.saya;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@RestController
public class MediaController {

    @Value("${kuroyukibot.saya.port}")
    private String kuroyukisayaport;

    @Value("${kuroyukibot.saya.path}")
    private String kuroyukisayapath;

    @Value("${kuroyukibot.saya.domain}")
    private String kuroyukisayadomain;

    private static final org.slf4j.Logger Logger = LoggerFactory.getLogger(MediaController.class);

    @RequestMapping(path="/media", method = RequestMethod.POST)
    public void send(@RequestBody String message) {
        Logger.info("Control Request Received: " + message);
        RestTemplate restTemplate = new RestTemplate();

        final String baseUrl =
                kuroyukisayadomain +
                kuroyukisayaport +
                kuroyukisayapath;

        JsonObject jsonObject = new JsonParser().parse(message).getAsJsonObject();

        //Body
        Map<String, Object> map = new HashMap<>();
        map.put("type", jsonObject.get("type").getAsString());
        map.put("action", jsonObject.get("action").getAsString());

        //Header
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        //Request Entity
        HttpEntity<Map<String, Object>> entity = new HttpEntity<>(map, headers);

        try {
            URI uri = new URI(baseUrl);
            ResponseEntity<String> result = restTemplate.postForEntity(uri, entity , String.class);
            Logger.info(result.getStatusCode().toString());
        }catch(Exception e){
            Logger.error(e.getMessage());
        }
    }
}
