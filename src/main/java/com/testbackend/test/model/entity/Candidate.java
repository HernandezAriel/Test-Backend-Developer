package com.testbackend.test.model.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.testbackend.test.model.enums.DocumentType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

import java.util.Date;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "candidates")
public class Candidate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCandidate;

    @NotNull(message = "First Name is required\n")
    private String firstName;

    @NotNull(message = "Last Name is required\n")
    private String lastName;

    private DocumentType documentType;

    @NotNull(message = "Document Number is required\n")
    @Size(min = 6, max = 9)
    private String documentNumber;

    @Past(message = "The date of birth must be in the past")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date birthDate;


}
