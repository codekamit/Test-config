package com.example.testproject.repository;

import com.example.testproject.model.Earning;
import com.example.testproject.model.State;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface EarningRepo extends JpaRepository<Earning, Long> {

    Optional<List<Earning>> findByState(State state);
}
