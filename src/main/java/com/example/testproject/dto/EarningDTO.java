package com.example.testproject.dto;

import com.example.testproject.model.State;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class EarningDTO {
    private String fund;
    private String clientShortName;
    private Double amount;
    private String currency;
    private State state;
}
