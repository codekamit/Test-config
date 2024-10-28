package com.example.testproject.service.chain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BeanFetcherService {

    private final ApplicationContext applicationContext;

    @Autowired
    public BeanFetcherService(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    public List<Object> getBeansByQualifiers(List<String> qualifierNames) {
        return qualifierNames.stream()
                .map(name -> applicationContext.getBean(name)) // Fetch bean by name
                .collect(Collectors.toList());                 // Collect beans into a list
    }
}
