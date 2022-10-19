package com.testbackend.test.model.dto;

import com.testbackend.test.model.entity.Candidate;
import com.testbackend.test.model.entity.Technology;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CandidateByTechnologyDto {

    private Candidate candidate;
    private Technology technology;
    private Long experience;
}