package com.example.testproject;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NetEarning {
    private Double totalAmount;
    private String currency;
    private String clientShortName;
    private String payableAt;
}
