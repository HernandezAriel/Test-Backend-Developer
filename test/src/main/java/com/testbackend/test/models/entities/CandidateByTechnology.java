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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

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

    @NotNull(message = "Candidate must not be null")
    @ManyToOne(fetch = FetchType.EAGER)
    private Candidate candidate;

    @NotNull(message = "Technology must not be null")
    @ManyToOne(fetch = FetchType.EAGER)
    private Technology technology;
    @PositiveOrZero(message = "Experience must not be less than 0")
    private Long experience;
}
