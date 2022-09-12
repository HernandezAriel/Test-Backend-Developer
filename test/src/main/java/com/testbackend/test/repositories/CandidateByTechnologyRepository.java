package com.testbackend.test.repositories;

import com.testbackend.test.models.entities.CandidateByTechnology;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CandidateByTechnologyRepository extends JpaRepository<CandidateByTechnology, Long>{

}
