package com.testbackend.test.repositories;

import com.testbackend.test.models.entities.Candidate;
import com.testbackend.test.models.entities.CandidateByTechnology;
import com.testbackend.test.models.entities.Technology;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface CandidateByTechnologyRepository extends JpaRepository<CandidateByTechnology, Long>{

    String CANDIDATES_BY_TECHNOLOGY = "SELECT cxt.id_candidate_for_technology, cxt.id_candidate, cxt.id_technology, cxt.years_experience " +
            "FROM candidates_for_technologies AS cxt " +
            "JOIN technologies AS t " +
            "ON cxt.id_technology = t.id_technology " +
            "WHERE t.name = ?1";
    CandidateByTechnology findByCandidateAndTechnology(Candidate candidate, Technology technology);
    List<CandidateByTechnology> findByCandidate(Candidate candidate);
    List<CandidateByTechnology> findByTechnology(Technology technology);
    @Query(value = CANDIDATES_BY_TECHNOLOGY, nativeQuery = true)
    List<CandidateByTechnology> findByNameTechnology(String nameTechnology);
}
