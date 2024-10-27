package com.example.testproject.repository;

import com.example.testproject.model.FundGroup;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface FundGroupRepo extends JpaRepository<FundGroup, Long> {
    Optional<List<FundGroup>> findByClientShortName(String clientShortName);
}
