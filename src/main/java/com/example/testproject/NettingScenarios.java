package com.example.testproject;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public interface NettingScenarios {
    public static List<NetEarning> noNettingPaymentFundLevel(List<Earning> clientEarnings, FundGroupDTO fundGroupDTO) {
        List<NetEarning> netEarnings = new ArrayList<>();
        clientEarnings.forEach(earning -> {
            NetEarning netEarning = new NetEarning();
            netEarning.setCurrency(earning.getCurrency());
            netEarning.setClientShortName(earning.getClientShortName());
            netEarning.setPayableAt(fundGroupDTO.getFundGroupMap().get(earning.getFund()));
            netEarning.setTotalAmount(earning.getAmount());
            netEarnings.add(netEarning);
        });
        return netEarnings;
    }

    public static List<NetEarning> noNettingFundLevel(List<Earning> clientEarnings) {
        List<NetEarning> netEarnings = new ArrayList<>();
        clientEarnings.forEach(earning -> {
            NetEarning netEarning = new NetEarning();
            netEarning.setCurrency(earning.getCurrency());
            netEarning.setClientShortName(earning.getClientShortName());
            netEarning.setPayableAt(earning.getFund());
            netEarning.setTotalAmount(earning.getAmount());
            netEarnings.add(netEarning);
        });
        return netEarnings;
    }

    public static List<NetEarning> noNettingClientLevel(List<Earning> clientEarnings, FundGroupDTO fundGroupDTO) {
        List<NetEarning> netEarnings = new ArrayList<>();
        clientEarnings.forEach(earning -> {
            NetEarning netEarning = new NetEarning();
            netEarning.setCurrency(earning.getCurrency());
            netEarning.setClientShortName(earning.getClientShortName());
            netEarning.setPayableAt(fundGroupDTO.getFundGroupMap().get(earning.getFund()));
            netEarning.setTotalAmount(earning.getAmount());
            netEarnings.add(netEarning);
        });
        return netEarnings;
    }

    public static List<NetEarning> NettingPaymentFundLevel(List<Earning> clientEarnings, FundGroupDTO fundGroupDTO) {
        Map<String, List<Earning>> earningsFundMap = new HashMap<>();
        clientEarnings.forEach(earning -> {
            if(!earningsFundMap.containsKey(fundGroupDTO.getFundGroupMap().get(earning.getFund()))) {
                earningsFundMap.put(fundGroupDTO.getFundGroupMap().get(earning.getFund()), new ArrayList<>());
            }
            earningsFundMap.get(fundGroupDTO.getFundGroupMap().get(earning.getFund())).add(earning);
        });
        List<NetEarning> netEarnings = new ArrayList<>();
        earningsFundMap.forEach((paymentFund, earnings) -> {
            NetEarning netEarning = new NetEarning();
            netEarning.setPayableAt(paymentFund);
            netEarning.setCurrency(earnings.get(0).getCurrency());
            netEarning.setClientShortName(earnings.get(0).getClientShortName());
            netEarning.setTotalAmount(earnings.stream()
                    .map(Earning::getAmount)
                    .reduce((x, y) -> x + y).get());
        });
    }

    public static List<NetEarning> NettingFundLevel() {

    }

    public static List<NetEarning> NettingClientLevel() {

    }
}
