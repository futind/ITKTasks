package ru.itk.simplesecurityconfig.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/v1")
public class SimpleController {

    @GetMapping("/public")
    public String publicMethod() {
        return "public";
    }

    @GetMapping("/private")
    public String privateMethod() {
        return "private";
    }
}
