package com.dubs.core.server.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dubs.core.server.entity.ContactDetails;
import com.dubs.core.server.entity.UserProfile;

import java.util.Optional;

@Repository
public interface UserProfileRepository extends JpaRepository<UserProfile,Integer> {

    Optional<UserProfile> findByUserId(Integer userid);


    UserProfile save(UserProfile newProfile);
    
}
