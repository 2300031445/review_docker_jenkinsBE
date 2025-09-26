package com.onlinevoting.repository;

import com.onlinevoting.entity.Vote;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VoteRepository extends JpaRepository<Vote, Long> {
}