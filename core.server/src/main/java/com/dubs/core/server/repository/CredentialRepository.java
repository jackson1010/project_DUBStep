package com.dubs.core.server.repository;

import com.dubs.core.server.entity.Credentials;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CredentialRepository extends JpaRepository<Credentials,Integer> {
    Optional<Credentials> findByUsername(String username);

    Optional<Credentials> findByUserId(Integer userid);

    Boolean existsByUsername(String username);

    Boolean existsByUserId(Integer userid);

    Credentials save(Credentials credentials);

}
