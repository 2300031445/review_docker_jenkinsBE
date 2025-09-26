package com.onlinevoting.service.impl;

import com.onlinevoting.entity.User;
import com.onlinevoting.entity.Election;
import com.onlinevoting.entity.Candidate;
import com.onlinevoting.repository.UserRepository;
import com.onlinevoting.repository.AdminRepository;
import com.onlinevoting.model.Admin;
import com.onlinevoting.repository.ElectionRepository;
import com.onlinevoting.repository.CandidateRepository;
import com.onlinevoting.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AdminServiceImpl implements AdminService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AdminRepository adminRepository;
    @Autowired
    private ElectionRepository electionRepository;
    @Autowired
    private CandidateRepository candidateRepository;

    @Override
    public Optional<User> login(String username, String password) {
        // 1. Check admin table first
        Optional<Admin> adminOpt = adminRepository.findByUsername(username);
        if (adminOpt.isPresent() && adminOpt.get().getPassword().equals(password)) {
            // Return a User object with role info for frontend
            User adminUser = new User();
            adminUser.setId(-1L); // -1 to indicate admin (not in users table)
            adminUser.setUsername(adminOpt.get().getUsername());
            adminUser.setPassword(""); // do not expose password
            adminUser.setEmail("admin@system");
            adminUser.setApproved(true);
            // Optionally, add a custom field or use a DTO for role info
            return Optional.of(adminUser);
        }
        // 2. Else check user table
        Optional<User> userOpt = userRepository.findByUsername(username);
        if (userOpt.isPresent() && userOpt.get().getPassword().equals(password)) {
            return userOpt;
        }
        return Optional.empty();
    }

    @Override
    public Object getDashboard() {
        // Return admin dashboard data (stub)
        return null;
    }

    @Override
    public List<User> getAllVoters() {
        return userRepository.findAll();
    }

    @Override
    public boolean approveVoter(Long userId) {
        Optional<User> userOpt = userRepository.findById(userId);
        if (userOpt.isPresent()) {
            User user = userOpt.get();
            user.setApproved(true);
            userRepository.save(user);
            return true;
        }
        return false;
    }

    @Override
    public boolean rejectVoter(Long userId) {
        Optional<User> userOpt = userRepository.findById(userId);
        if (userOpt.isPresent()) {
            User user = userOpt.get();
            user.setApproved(false);
            userRepository.save(user);
            return true;
        }
        return false;
    }

    @Override
    public Election createElection(Election election) {
        return electionRepository.save(election);
    }

    @Override
    public Election updateElection(Long electionId, Election election) {
        Optional<Election> existing = electionRepository.findById(electionId);
        if (existing.isPresent()) {
            Election e = existing.get();
            e.setName(election.getName());
            e.setDescription(election.getDescription());
            e.setActive(election.isActive());
            return electionRepository.save(e);
        }
        throw new RuntimeException("Election not found");
    }

    @Override
    public boolean deleteElection(Long electionId) {
        if (electionRepository.existsById(electionId)) {
            electionRepository.deleteById(electionId);
            return true;
        }
        return false;
    }

    @Override
    public Object getElectionResults(Long electionId) {
        // Return election results (stub)
        return null;
    }

    @Override
    public Candidate addCandidate(Long electionId, Candidate candidate) {
        Optional<Election> electionOpt = electionRepository.findById(electionId);
        if (electionOpt.isPresent()) {
            candidate.setElection(electionOpt.get());
            return candidateRepository.save(candidate);
        }
        throw new RuntimeException("Election not found");
    }

    @Override
    public Candidate updateCandidate(Long candidateId, Candidate candidate) {
        Optional<Candidate> existing = candidateRepository.findById(candidateId);
        if (existing.isPresent()) {
            Candidate c = existing.get();
            c.setName(candidate.getName());
            return candidateRepository.save(c);
        }
        throw new RuntimeException("Candidate not found");
    }

    @Override
    public boolean deleteCandidate(Long candidateId) {
        if (candidateRepository.existsById(candidateId)) {
            candidateRepository.deleteById(candidateId);
            return true;
        }
        return false;
    }
}
