package com.example.testproject;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.List;
import java.util.Map;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class NettingInputDTO {
    private ClientPreferenceDTO clientPreferenceDTO;
    private FundGroupDTO fundGroupDTO;
    private Map<String, List<Earning>> clientEarnings;
}
