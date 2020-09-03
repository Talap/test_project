package com.example.test_project.repository;


import com.example.test_project.models.ContactDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContactDetailsRepository extends JpaRepository<ContactDetails, Long> {

    ContactDetails findFirstByIdAndDeletedAtIsNull(Long id);
    List<ContactDetails> findAllByClientId(Long id);

}
