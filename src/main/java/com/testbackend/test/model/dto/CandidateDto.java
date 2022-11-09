package com.testbackend.test.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.testbackend.test.model.enums.DocumentType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CandidateDto {

    private Long idCandidate;
    private String firstName;
    private String lastName;
    private DocumentType documentType;
    private String documentNumber;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date birthDate;

}
