package com.example.testproject.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
public class ClientPreference {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String clientShortName;
    private Boolean netting;
    @Enumerated(EnumType.STRING)
    private GroupingType groupingType;
}
