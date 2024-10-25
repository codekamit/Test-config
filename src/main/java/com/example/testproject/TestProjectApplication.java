package com.example.testproject;

import com.example.testproject.service.ClientConfigService;
import com.example.testproject.service.Preprocessor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TestProjectApplication {
    public static void main(String[] args) {
        var context = SpringApplication.run(TestProjectApplication.class, args);
        ClientConfigService clientConfigService = context.getBean(ClientConfigService.class);
        Preprocessor preprocessor = context.getBean(Preprocessor.class);
        preprocessor.process(clientConfigService.getTestEarnings());
    }
}
