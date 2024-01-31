package com.dubs.core.server.service;

import com.dubs.core.server.dto.AuthRequest;
import com.dubs.core.server.dto.ProfileDTO;
import com.dubs.core.server.entity.ContactDetails;
import com.dubs.core.server.entity.Credentials;
import com.dubs.core.server.entity.UserProfile;
import com.dubs.core.server.enums.Authority;
import com.dubs.core.server.repository.ContactDetailsRepository;
import com.dubs.core.server.repository.CredentialRepository;
import com.dubs.core.server.repository.UserProfileRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserDetailsService {

    @Autowired
    CredentialRepository credentialRepository;

    @Autowired
    UserProfileRepository userProfileRepository;

    @Autowired
    ContactDetailsRepository contactDetailsRepository;
    

    @Autowired
    PasswordEncoder passwordEncoder;


    public UserDetails loadUserByUsername(String username){
        Optional<Credentials> creds = credentialRepository.findByUsername(username);
        if(creds.isEmpty()) return null; //TODO: THROW EXCEPTION OR HANDLE
        return creds.get();
    }

    public Optional<Credentials> findCredentialsByUserId(Integer userid){
        return credentialRepository.findByUserId(userid);
    }

    public Boolean existsByUsername(String username){
        return credentialRepository.existsByUsername(username);
    }

    public Boolean existsByUserId(Integer userid){
        return credentialRepository.existsByUserId(userid);
    }

    @Transactional
    public Credentials createNewUser(AuthRequest signupRequest){
        Credentials newCreds = new Credentials(null, signupRequest.getUsername(), passwordEncoder.encode(signupRequest.getPassword()),
                true,true,true,true,
               Authority.USER);
        Credentials createdCreds = credentialRepository.save(newCreds);

        UserProfile emptyProfile = new UserProfile();
        emptyProfile.setUserId(createdCreds.getUserId());
        userProfileRepository.save(emptyProfile);

        ContactDetails emptyContactDetails = new ContactDetails();
        emptyContactDetails.setUserId(createdCreds.getUserId());
        contactDetailsRepository.save(emptyContactDetails);

        return createdCreds;
    }


    @Transactional
    public boolean saveNewProfile(ProfileDTO profileUpdateDTO){
        userProfileRepository.save(profileUpdateDTO.getUserProfile());
        contactDetailsRepository.save(profileUpdateDTO.getContactDetails());
        return true;
    }

    public UserProfile save(UserProfile userProfile){
        return userProfileRepository.save(userProfile);
    }

    public ContactDetails save(ContactDetails contactDetails){
        return contactDetailsRepository.save(contactDetails);
    }

    public Optional<UserProfile> findProfileByUserId(Integer userID){
        return userProfileRepository.findByUserId(userID);
    }

    public Optional<ContactDetails> findContactsByUserId(Integer userID){
        return contactDetailsRepository.findByUserId(userID);
    }


}
