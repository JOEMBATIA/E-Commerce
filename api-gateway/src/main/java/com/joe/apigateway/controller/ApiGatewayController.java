package com.joe.apigateway.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ApiGatewayController {

    @GetMapping("/apigateway")
    public String message(){
        return "Hello user, your ApiGateway is running :)";
    }
}
