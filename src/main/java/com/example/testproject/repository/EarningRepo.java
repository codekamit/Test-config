package com.example.testproject.repository;

import com.example.testproject.model.Earning;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EarningRepo extends JpaRepository<Earning, Long> {
}
