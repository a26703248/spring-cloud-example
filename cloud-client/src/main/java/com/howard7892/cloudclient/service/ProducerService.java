package com.howard7892.cloudclient.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "SPRING-CLOUD-PRODUCER-7003")
public interface ProducerService {

    @GetMapping(value = "/producer/get")
    public String doGetProducer();

}
