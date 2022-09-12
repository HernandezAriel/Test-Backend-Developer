package com.testbackend.test.models.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.testbackend.test.models.enums.DocumentType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CandidateDto {

    private String firstName;
    private String lastName;
    private DocumentType documentType;
    private String document;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date birtdate;
    private List<ExperienceDto> technologies;
}
