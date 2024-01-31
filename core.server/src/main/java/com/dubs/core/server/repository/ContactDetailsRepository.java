package com.dubs.core.server.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dubs.core.server.entity.ContactDetails;

import java.util.Optional;

@Repository
public interface ContactDetailsRepository extends JpaRepository<ContactDetails,Integer> {

    Optional<ContactDetails> findByUserId(Integer userid);

    ContactDetails save(ContactDetails contactDetails);
    
}
