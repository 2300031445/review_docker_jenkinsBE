package com.onlinevoting.controller;

import com.onlinevoting.entity.User;
import com.onlinevoting.model.Admin;
import com.onlinevoting.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@CrossOrigin("*")
@RestController
@RequestMapping("/api")
public class AuthController {

    @Autowired
    private AdminService adminService;

    // Accepts JSON: { "username": "...", "password": "..." }
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> loginRequest) {
        String username = loginRequest.get("username");
        String password = loginRequest.get("password");
        Optional<User> found = adminService.login(username, password);
        if (found.isPresent()) {
            User user = found.get();
            Map<String, Object> response = new HashMap<>();
            response.put("id", user.getId());
            response.put("username", user.getUsername());
            response.put("email", user.getEmail());
            response.put("approved", user.isApproved());
            // If admin, set role explicitly
            if (user.getId() == -1L) {
                response.put("role", "admin");
            } else {
                response.put("role", "user");
            }
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.status(401).body("Invalid credentials");
        }
    }
}
