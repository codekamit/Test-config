package com.example.testproject.service;

import com.example.testproject.model.Earning;
import com.example.testproject.model.NetEarning;
import com.example.testproject.repository.NetEarningRepo;
import com.example.testproject.service.chain.HandlerChain;
import com.example.testproject.service.handler.BaseHandler;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class Preprocessor {
    private final NetEarningRepo netEarningRepo;
    public Preprocessor(NetEarningRepo netEarningRepo) {
        this.netEarningRepo = netEarningRepo;
    }

    public List<NetEarning> process(List<Earning> earnings) {
        Map<String, Map<String, List<Earning>>> currencyAndClientWiseEarnings = earnings.stream()
                .collect(Collectors.groupingBy(Earning::getCurrency,
                        Collectors.groupingBy(Earning::getClientShortName)));

        currencyAndClientWiseEarnings.forEach((currency, clientWiseEarnings) -> {
            if(currency.equals("CAD")) {
                BaseHandler cadRootHandler = HandlerChain.getCADHandlerChain();
                cadRootHandler.handle(clientWiseEarnings);
            }
            else {
                throw new RuntimeException("This currency is not supported");
            }
        });
        return netEarningRepo.findAll();
    }
}
