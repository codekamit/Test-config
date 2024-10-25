package com.example.testproject;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Preprocessor {

    public List<NetEarning> process(List<Earning> earnings) {
        Map<String, Map<String, List<Earning>>> currencyWiseEarnings = earnings.stream()
                .collect(Collectors.groupingBy(Earning::getCurrency,
                        Collectors.groupingBy(Earning::getClientShortName)));
    }
}
