package com.example.testproject.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
public class NetEarning {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Double totalAmount;
    private String currency;
    private String clientShortName;
    private String payableAt;
    @OneToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    @JoinTable(name="net_earning_map_earning",
    joinColumns = @JoinColumn(name="net_earning_id"),
    inverseJoinColumns = @JoinColumn(name="earning_id"))
    private List<Earning> earnings;
}
