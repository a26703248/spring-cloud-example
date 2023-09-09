package com.howard7892.cloudclient.controller;

import com.howard7892.cloudclient.service.ProducerService;
import com.netflix.appinfo.InstanceInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.function.Consumer;

@RestController
@RequestMapping("/api")
public class ApiController {

    @Autowired
    private ProducerService producerService;

    // openFeign
    @GetMapping("/feign_api")
    public String feignApi(){
        return "aggregationApi/"+producerService.doGetProducer();
    }

    // api gateway
    @Autowired
    private DiscoveryClient discoveryClient;

    @GetMapping("/discovery_api")
    public String discoverApi(){
        List<ServiceInstance> instances = discoveryClient.getInstances("SPRING-CLOUD-PRODUCER-7003");
        ServiceInstance serviceInstance = instances.get(0);
        String host = serviceInstance.getUri().toString();
        String producerUrl = host + "/producer/get";
        RequestEntity<Void> request = RequestEntity.get(producerUrl)
                .header("Accept", MediaType.APPLICATION_JSON_VALUE)
                .build();
        ResponseEntity<String> response = new RestTemplate().exchange(request, String.class);
        String body = response.getBody();
        return "discoverApi/"+body;
    }

}
