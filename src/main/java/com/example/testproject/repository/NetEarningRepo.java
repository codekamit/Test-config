package com.example.testproject.repository;

import com.example.testproject.model.NetEarning;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NetEarningRepo extends JpaRepository<NetEarning, Long> {
}
