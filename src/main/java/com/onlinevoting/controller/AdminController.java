package com.onlinevoting.controller;


import com.onlinevoting.entity.User;
import com.onlinevoting.entity.Election;
import com.onlinevoting.entity.Candidate;
import com.onlinevoting.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @PostMapping("/login")
    public ResponseEntity<User> login(@RequestBody User user) {
        Optional<User> found = adminService.login(user.getUsername(), user.getPassword());
        return found.map(ResponseEntity::ok).orElse(ResponseEntity.status(401).build());
    }

    @GetMapping("/dashboard")
    public ResponseEntity<Object> getDashboard() {
        Object dashboard = adminService.getDashboard();
        return ResponseEntity.ok(dashboard);
    }

    @GetMapping("/voters")
    public ResponseEntity<List<User>> getAllVoters() {
        return ResponseEntity.ok(adminService.getAllVoters());
    }

    @PutMapping("/voters/{id}/approve")
    public ResponseEntity<?> approveVoter(@PathVariable Long id) {
        boolean success = adminService.approveVoter(id);
        return success ? ResponseEntity.ok().build() : ResponseEntity.notFound().build();
    }

    @PutMapping("/voters/{id}/reject")
    public ResponseEntity<?> rejectVoter(@PathVariable Long id) {
        boolean success = adminService.rejectVoter(id);
        return success ? ResponseEntity.ok().build() : ResponseEntity.notFound().build();
    }

    @GetMapping("/elections")
    public ResponseEntity<List<Election>> getElections() {
        // Implement fetching all elections if needed
        return ResponseEntity.ok().build();
    }

    @PostMapping("/elections")
    public ResponseEntity<Election> createElection(@RequestBody Election election) {
        Election created = adminService.createElection(election);
        return ResponseEntity.ok(created);
    }

    @PutMapping("/elections/{id}")
    public ResponseEntity<Election> updateElection(@PathVariable Long id, @RequestBody Election election) {
        Election updated = adminService.updateElection(id, election);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/elections/{id}")
    public ResponseEntity<?> deleteElection(@PathVariable Long id) {
        boolean success = adminService.deleteElection(id);
        return success ? ResponseEntity.ok().build() : ResponseEntity.notFound().build();
    }

    @GetMapping("/elections/{id}/results")
    public ResponseEntity<Object> getElectionResults(@PathVariable Long id) {
        Object results = adminService.getElectionResults(id);
        return ResponseEntity.ok(results);
    }

    @PostMapping("/elections/{id}/candidates")
    public ResponseEntity<Candidate> addCandidate(@PathVariable Long id, @RequestBody Candidate candidate) {
        Candidate created = adminService.addCandidate(id, candidate);
        return ResponseEntity.ok(created);
    }

    @PutMapping("/candidates/{id}")
    public ResponseEntity<Candidate> updateCandidate(@PathVariable Long id, @RequestBody Candidate candidate) {
        Candidate updated = adminService.updateCandidate(id, candidate);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/candidates/{id}")
    public ResponseEntity<?> deleteCandidate(@PathVariable Long id) {
        boolean success = adminService.deleteCandidate(id);
        return success ? ResponseEntity.ok().build() : ResponseEntity.notFound().build();
    }
}
