package com.testbackend.test.models.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.testbackend.test.models.enums.DocumentType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "candidates")
public class Candidate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCandidate;
    private String firstName;
    private String lastName;
    private DocumentType documentType;
    private String documentNumber;
    private Date birthdate;


}
