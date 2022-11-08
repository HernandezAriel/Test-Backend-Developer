package com.testbackend.test.repository;

import com.testbackend.test.model.dto.CandidateDto;
import com.testbackend.test.model.dto.TechnologyDto;
import com.testbackend.test.model.entity.Candidate;
import com.testbackend.test.model.entity.CandidateByTechnology;
import com.testbackend.test.model.entity.Technology;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface CandidateByTechnologyRepository extends JpaRepository<CandidateByTechnology, Long>{

    String CANDIDATES_BY_TECHNOLOGY = "SELECT cbt.id_candidate_by_technology, cbt.id_candidate, cbt.id_technology, cbt.experience " +
            "FROM candidates_by_technologies AS cbt " +
            "JOIN technologies AS t " +
            "ON cbt.id_technology = t.id_technology " +
            "WHERE t.name = ?1";
    CandidateByTechnology findByCandidateAndTechnology(Candidate candidate, Technology technology);
    List<CandidateByTechnology> findByCandidate(CandidateDto candidateDto);
    List<CandidateByTechnology> findByTechnology(TechnologyDto technologyDto);
    @Query(value = CANDIDATES_BY_TECHNOLOGY, nativeQuery = true)
    List<CandidateByTechnology> findByNameTechnology(String nameTechnology);
}
