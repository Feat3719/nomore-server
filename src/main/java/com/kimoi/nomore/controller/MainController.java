package com.kimoi.nomore.controller;

import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
public class MainController {


    @GetMapping("/api/test")
    public Map<String, String> testMethod(@RequestParam Map<String, String> response) {
        response.put("data", "springboot to react");
        return response;
        
    }
    
    
}
