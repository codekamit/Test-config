package com.example.testproject.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Earning {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String fund;
    private String clientShortName;
    private Double amount;
    private String currency;
    @Enumerated(EnumType.STRING)
    private State state;

    public Earning(String fund, String clientShortName, Double amount, String currency, State state) {
        this.fund = fund;
        this.clientShortName = clientShortName;
        this.amount = amount;
        this.currency = currency;
        this.state = state;
    }
}
