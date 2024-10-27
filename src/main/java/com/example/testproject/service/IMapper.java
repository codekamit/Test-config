package com.example.testproject.service;

import com.example.testproject.dto.FundGroupDTO;
import com.example.testproject.model.FundGroup;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface IMapper {
    static FundGroupDTO getFundGroupDTOFromModel(List<FundGroup> fundGroups) {
        String clientShortName = fundGroups.get(0).getClientShortName();
        Map<String, String> fundGroupMap = new HashMap<>();
        fundGroups.forEach(fundGroup -> {
            if(!fundGroupMap.containsKey(fundGroup.getFund())) {
                fundGroupMap.put(fundGroup.getFund(), fundGroup.getPaymentFund());
            }
            else {
                throw new RuntimeException("This fund already has a mapping to PaymentFund");
            }
        });
        return FundGroupDTO.builder()
                .clientShortName(clientShortName)
                .fundGroupMap(fundGroupMap)
                .build();
    }

    static List<FundGroup> getFundGroupFromDTO(FundGroupDTO fundGroupDTO) {
        String clientShortName = fundGroupDTO.getClientShortName();
        List<FundGroup> fundGroups = new ArrayList<>();
        fundGroupDTO.getFundGroupMap()
                .forEach((fund, paymentFund) -> {
                    FundGroup fundGroup = new FundGroup();
                    fundGroup.setClientShortName(clientShortName);
                    fundGroup.setFund(fund);
                    fundGroup.setPaymentFund(paymentFund);
                    fundGroups.add(fundGroup);
                });
        return fundGroups;
    }
}
