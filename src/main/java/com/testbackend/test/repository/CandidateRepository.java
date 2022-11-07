package com.testbackend.test.repository;

import com.testbackend.test.model.entity.Candidate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface CandidateRepository extends JpaRepository<Candidate, Long> {
    Optional<Candidate> findByIdCandidateOrDocumentNumber(Long idCandidate, String document);
}
