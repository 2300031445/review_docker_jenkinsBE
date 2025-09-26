package com.onlinevoting.service;

import com.onlinevoting.entity.Vote;
import java.util.Optional;

public interface VoteService {
    boolean castVote(Long userId, Long electionId, Long candidateId);
    int countVotes(Long candidateId);
    Optional<Vote> getUserVote(Long userId, Long electionId);
}