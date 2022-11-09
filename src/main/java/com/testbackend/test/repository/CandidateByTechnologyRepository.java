package com.testbackend.test.repository;

import com.testbackend.test.model.entity.CandidateByTechnology;
import com.testbackend.test.projection.CandidateByTechnologyProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface CandidateByTechnologyRepository extends JpaRepository<CandidateByTechnology, Long> {

    String CANDIDATES_BY_TECHNOLOGY = "SELECT c.FIRST_NAME,c.LAST_NAME,c.DOCUMENT_TYPE,c.DOCUMENT_NUMBER,c.BIRTH_DATE,t.NAME,ct.EXPERIENCE " +
            "FROM CANDIDATES_BY_TECHNOLOGIES As ct INNER JOIN CANDIDATES As c ON ct.ID_CANDIDATE = c.ID_CANDIDATE " +
            "INNER JOIN TECHNOLOGIES As t ON ct.ID_TECHNOLOGY = t.ID_TECHNOLOGY WHERE t.NAME = ?1";

    @Query(value = CANDIDATES_BY_TECHNOLOGY, nativeQuery = true)
    List<CandidateByTechnologyProjection> findByNameTechnology(String nameTechnology);
}
