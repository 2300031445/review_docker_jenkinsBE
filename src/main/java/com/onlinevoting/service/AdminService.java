package com.onlinevoting.service;

import com.onlinevoting.entity.User;
import com.onlinevoting.entity.Election;
import com.onlinevoting.entity.Candidate;
import java.util.List;
import java.util.Optional;

public interface AdminService {
    Optional<User> login(String username, String password);
    Object getDashboard();
    List<User> getAllVoters();
    boolean approveVoter(Long userId);
    boolean rejectVoter(Long userId);
    Election createElection(Election election);
    Election updateElection(Long electionId, Election election);
    boolean deleteElection(Long electionId);
    Object getElectionResults(Long electionId);
    Candidate addCandidate(Long electionId, Candidate candidate);
    Candidate updateCandidate(Long candidateId, Candidate candidate);
    boolean deleteCandidate(Long candidateId);
}