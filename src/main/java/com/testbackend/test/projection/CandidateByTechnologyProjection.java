package com.testbackend.test.projection;

import com.testbackend.test.model.enums.DocumentType;
import org.springframework.beans.factory.annotation.Value;

import java.util.Date;

public interface CandidateByTechnologyProjection {

    @Value("#{target.firstName}")
    String getFirstName();

    @Value("#{target.lastName}")
    String getLastName();

    @Value("#{target.documentType}")
    DocumentType getDocumentType();

    @Value("#{target.documentNumber}")
    String getDocumentNumber();

    @Value("#{target.birthDate}")
    Date getBirthDate();

    @Value("#{target.name}")
    String getNameTechnology();

    @Value("#{target.experience}")
    String getExperience();
}