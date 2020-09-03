package com.example.test_project.repository;

import com.example.test_project.models.ContactType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContactTypeRepository extends JpaRepository<ContactType, Long> {
    ContactType findFirstById(Long id);
}
