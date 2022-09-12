package com.testbackend.test.models.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "candidatesByTechnologies")
public class CandidateByTechnology {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCandidateByTechnology;

    @ManyToOne(fetch = FetchType.EAGER)
    private Candidate candidate;

    @ManyToOne(fetch = FetchType.EAGER)
    private Technology technology;
    private Long experience;
}
