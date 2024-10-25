package com.example.testproject;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class ClientPreferenceDTO {
    private String clientShortName;
    private Boolean netting;
    private GroupingType groupingType;
}
