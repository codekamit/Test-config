package com.example.testproject.repository;

import com.example.testproject.model.ClientPreference;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientPreferenceRepo extends JpaRepository<ClientPreference, Long> {
}
