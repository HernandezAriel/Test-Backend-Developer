package com.testbackend.test.projection;

import com.testbackend.test.model.enums.DocumentType;
import org.springframework.beans.factory.annotation.Value;

import java.util.Date;

public interface CandidateByTechnologyProjection {

    @Value("#{target.first_name}")
    String getName();

    @Value("#{target.last_name}")
    String getLastName();

    @Value("#{target.document_type}")
    DocumentType getDniType();

    @Value("#{target.document_number}")
    String getDni();

    @Value("#{target.birth_date}")
    Date getBirthDate();

    @Value("#{target.name}")
    String getNameTechnology();

    @Value("#{target.experience}")
    String getExperience();
}