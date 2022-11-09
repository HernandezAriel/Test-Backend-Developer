package com.testbackend.test.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CandidateByTechnologyAddDto {

    private Long candidateId;

    private Long technologyId;

    private Long experience;
}

