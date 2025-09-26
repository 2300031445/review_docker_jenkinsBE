package com.onlinevoting.repository;

import com.onlinevoting.entity.Election;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ElectionRepository extends JpaRepository<Election, Long> {
}