package com.howard7892.springsecurityoauthserver.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyController {

    @GetMapping("/")
    public String index(){
        return "Index Page";
    }

    @GetMapping("/callback")
    public String callback()  {
        return "Callback page";
    }

}
