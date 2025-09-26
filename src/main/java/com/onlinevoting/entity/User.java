package com.onlinevoting.entity;

import jakarta.persistence.*;
import lombok.*;
import java.util.List;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private boolean approved = false;

    @OneToMany(mappedBy = "user")
    private List<Vote> votes;

    @OneToMany(mappedBy = "user")
    private List<AuditLog> auditLogs;
}