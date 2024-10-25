package com.example.testproject;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Earning {
    private String fund;
    private String clientShortName;
    private Double amount;
    private String currency;
}
