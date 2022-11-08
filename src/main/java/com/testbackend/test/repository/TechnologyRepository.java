package com.testbackend.test.repository;

import com.testbackend.test.model.entity.Technology;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TechnologyRepository extends JpaRepository<Technology, Long> {
    Optional<Technology> findByNameAndVersion(String name, String version);

    Technology findByName(String name);
}
