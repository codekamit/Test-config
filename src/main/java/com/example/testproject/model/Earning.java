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
}
