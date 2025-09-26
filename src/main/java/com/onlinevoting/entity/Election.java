package com.onlinevoting.entity;

import jakarta.persistence.*;
import lombok.*;
import java.util.List;

@Entity
@Table(name = "elections")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Election {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private boolean active;

    @OneToMany(mappedBy = "election")
    private List<Candidate> candidates;

    @OneToMany(mappedBy = "election")
    private List<Vote> votes;

    @OneToMany(mappedBy = "election")
    private List<AuditLog> auditLogs;
}