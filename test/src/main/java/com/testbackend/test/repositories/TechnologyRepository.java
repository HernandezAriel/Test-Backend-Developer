package com.testbackend.test.repositories;

import com.testbackend.test.models.entities.Technology;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TechnologyRepository extends JpaRepository<Technology, Long> {

    Technology findByNameAndVersion(String name, String version);
    Technology findByName(String name);
}
