package com.testbackend.test.models.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.testbackend.test.models.enums.DocumentType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Past;
import java.util.Date;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "candidates")
public class Candidate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCandidate;

    @NotBlank(message = "First Name is required\n")
    private String firstName;

    @NotBlank(message = "Last Name is required\n")
    private String lastName;

    @NotBlank(message = "First Name is required\n")
    private DocumentType documentType;

    @NotBlank(message = "Document Number is required\n")
    private String documentNumber;

    @Past(message = "The date of birth must be in the past")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date birthdate;


}
