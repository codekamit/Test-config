package com.example.testproject.service.chain;

import com.example.testproject.config.SpringContextHolder;
import com.example.testproject.repository.NetEarningRepo;
import com.example.testproject.service.ClientConfigService;
import com.example.testproject.service.handler.BaseHandler;
import com.example.testproject.service.handler.ClientPreferenceHandler;
import com.example.testproject.service.handler.NettingHandler;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@Component
public interface HandlerChain {
    public static BaseHandler getCADHandlerChain() {
        ApplicationContext context = SpringContextHolder.getApplicationContext();
        return new ClientPreferenceHandler(context.getBean(ClientConfigService.class),
                new NettingHandler(context.getBean(NetEarningRepo.class)));
    }
}
