package com.onlinevoting.entity;

import jakarta.persistence.*;
import lombok.*;
import java.util.List;

@Entity
@Table(name = "candidates")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Candidate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @ManyToOne
    @JoinColumn(name = "election_id")
    private Election election;

    @OneToMany(mappedBy = "candidate")
    private List<Vote> votes;
}