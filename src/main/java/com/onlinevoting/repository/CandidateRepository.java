package com.onlinevoting.repository;

import com.onlinevoting.entity.Candidate;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CandidateRepository extends JpaRepository<Candidate, Long> {
}