package com.onlinevoting.controller;


import com.onlinevoting.entity.User;
import com.onlinevoting.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;

@CrossOrigin("*")
@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<User> signup(@RequestBody User user) {
        User created = userService.registerUser(user);
        return ResponseEntity.ok(created);
    }

    // Login endpoint removed; handled by AuthController

    @PostMapping("/logout")
    public ResponseEntity<Void> logout(@RequestParam Long userId) {
        userService.logout(userId);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/profile/{id}")
    public ResponseEntity<User> getProfile(@PathVariable Long id) {
        Optional<User> user = userService.getProfile(id);
        return user.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/profile/{id}")
    public ResponseEntity<User> updateProfile(@PathVariable Long id, @RequestBody User user) {
        User updated = userService.updateProfile(id, user);
        return ResponseEntity.ok(updated);
    }

    @GetMapping("/dashboard/{id}")
    public ResponseEntity<Object> getDashboard(@PathVariable Long id) {
        Object dashboard = userService.getDashboard(id);
        return ResponseEntity.ok(dashboard);
    }

    @PostMapping("/vote")
    public ResponseEntity<?> castVote(@RequestParam Long userId, @RequestParam Long electionId, @RequestParam Long candidateId) {
        boolean success = userService.castVote(userId, electionId, candidateId);
        if (success) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.badRequest().body("Vote failed");
        }
    }
}
