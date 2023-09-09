package com.example.cloudproducer.controller;


import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;

@RestController
@RequestMapping("/producer")
public class ProducerController {

    @GetMapping("/get")
    @HystrixCommand(fallbackMethod = "getRollbackApi")
    public String doGetProducer(){
        int random = new Random().nextInt(10);
        if((random%2) == 0){
            throw new RuntimeException("發生錯誤 ");
        }
        return "producer";
    }

    public String getRollbackApi(){
        return "error rollback";
    }

}
