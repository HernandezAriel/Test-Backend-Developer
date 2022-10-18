package com.testbackend.test.repository;

import com.testbackend.test.model.entity.Candidate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CandidateRepository extends JpaRepository<Candidate, Long> {
    Candidate findByIdCandidateOrDocument(Long idCandidate, String document);
}
