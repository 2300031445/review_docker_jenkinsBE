package com.onlinevoting.service.impl;

import com.onlinevoting.entity.User;
import com.onlinevoting.repository.UserRepository;
import com.onlinevoting.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User registerUser(User user) {
        // Save password as plain text (⚠️ not secure, for development only)
        user.setApproved(false); // require admin approval
        return userRepository.save(user);
    }

    @Override
    public Optional<User> login(String username, String password) {
        Optional<User> userOpt = userRepository.findByUsername(username);
        if (userOpt.isPresent()) {
            User u = userOpt.get();
            if (password.equals(u.getPassword()) && u.isApproved()) {
                return Optional.of(u);
            }
        }
        return Optional.empty();
    }

    @Override
    public void logout(Long userId) {
        // TODO: implement session/token invalidation if needed
    }

    @Override
    public Optional<User> getProfile(Long userId) {
        return userRepository.findById(userId);
    }

    @Override
    public User updateProfile(Long userId, User user) {
        User u = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (user.getEmail() != null) {
            u.setEmail(user.getEmail());
        }
        if (user.getPassword() != null && !user.getPassword().isEmpty()) {
            u.setPassword(user.getPassword()); // plain text
        }
        // TODO: update other fields if needed
        return userRepository.save(u);
    }

    @Override
    public Object getDashboard(Long userId) {
        // TODO: implement dashboard logic, e.g., show votes, election info, logs
        return "Dashboard data for user " + userId;
    }

    @Override
    public boolean castVote(Long userId, Long electionId, Long candidateId) {
        // TODO: implement voting logic in future
        return false;
    }

    @Override
    public boolean hasUserVoted(Long userId, Long electionId) {
        // TODO: implement vote check in future
        return false;
    }
}
