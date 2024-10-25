package com.example.testproject.service;

import com.example.testproject.config.SpringContextHolder;
import com.example.testproject.repository.NetEarningRepo;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@Component
public interface HandlerChain {
    public static BaseHandler getCADHandlerChain() {
        ApplicationContext context = SpringContextHolder.getApplicationContext();
        ClientConfigService clientConfigService = context.getBean(ClientConfigService.class);
        NetEarningRepo netEarningRepo = context.getBean(NetEarningRepo.class);
        ClientPreferenceHandler clientPreferenceHandler = new ClientPreferenceHandler(clientConfigService,
                new NettingHandler(netEarningRepo));
        return clientPreferenceHandler;
    }
}
