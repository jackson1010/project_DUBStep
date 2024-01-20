package com.dubs.core.server.security;

import com.dubs.core.server.entity.Credentials;
import com.dubs.core.server.repository.CredentialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    CredentialRepository credentialRepo;

    public UserDetails loadUserByUsername(String username){
        Optional<Credentials> creds = credentialRepo.findByUsername(username);
        if(creds.isEmpty()) return null; //TODO: THROW EXCEPTION OR HANDLe
        return creds.get();
    }

    public Optional<Credentials> findByUserId(Integer userid){
        return credentialRepo.findByUserId(userid);
    }

    public Boolean existsByUsername(String username){
        return credentialRepo.existsByUsername(username);
    }

    public Boolean existsByUserId(Integer userid){
        return credentialRepo.existsByUserId(userid);
    }

    public Credentials save(Credentials creds){
        return credentialRepo.save(creds);
    }

}
