package com.paimon.QLBanVePaimon.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("manager")
public class ManangerController {
    
    @GetMapping
    public String getMethodName() {
        String test = "1234";
        System.out.println(test);
        return test;
    }
    
}
