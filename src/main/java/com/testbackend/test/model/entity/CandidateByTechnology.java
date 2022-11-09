package com.testbackend.test.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "candidatesByTechnologies")
public class CandidateByTechnology {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull(message = "Id is required\n")
    private Long idCandidateByTechnology;

    @NotBlank(message = "Candidate must not be null")
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_candidate")
    private Candidate candidate;

    @NotBlank(message = "Technology must not be null")
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_technology")
    private Technology technology;

    @PositiveOrZero(message = "Experience must not be less than 0")
    @NotNull(message = "experience must not be null")
    private Long experience;
}
