package com.onlinevoting.service;

import com.onlinevoting.entity.User;
import java.util.Optional;

public interface UserService {
    User registerUser(User user);
    Optional<User> login(String username, String password);
    void logout(Long userId);
    Optional<User> getProfile(Long userId);
    User updateProfile(Long userId, User user);
    Object getDashboard(Long userId);
    boolean castVote(Long userId, Long electionId, Long candidateId);
    boolean hasUserVoted(Long userId, Long electionId);
}