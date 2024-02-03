package com.dubs.core.server.controller;

import com.dubs.core.server.dto.AuthRequest;
import com.dubs.core.server.dto.AuthResponse;
import com.dubs.core.server.dto.ProfileDTO;
import com.dubs.core.server.entity.ContactDetails;
import com.dubs.core.server.entity.Credentials;
import com.dubs.core.server.entity.UserProfile;
import com.dubs.core.server.security.JWTService;
import com.dubs.core.server.service.UserServiceImpl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.json.Json;
import jakarta.json.JsonObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("api/auth")
@Slf4j
@CrossOrigin(origins="*")
public class AuthController {
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserServiceImpl userDetailsSvc;

    @Autowired
    JWTService jwtService;

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    PasswordEncoder passwordEncoder;

    /**
     * @return 200 Success with userId for subsequent calls or 400 BadRequest if username already exists
     */
    @PostMapping("/createAccount")
    public ResponseEntity<String> registerUser(@RequestBody AuthRequest signupRequest){
        log.info("POST Request:{}",signupRequest);

        if(userDetailsSvc.existsByUsername(signupRequest.getUsername())){
            //TODO: CHECK CALL IF OK
            JsonObject response = Json.createObjectBuilder().add("Create Account Error", "Username already exists").build();
            return ResponseEntity.badRequest().body(response.toString());
        }

        signupRequest.setPassword(passwordEncoder.encode(signupRequest.getPassword()));
        //TODO:TEST user creation with null, else 
        Credentials createdUser = userDetailsSvc.createNewUser(signupRequest);
        JsonObject response = Json.createObjectBuilder().add("Create Account Success",createdUser.getUserId()).build();
        return ResponseEntity.ok().body(response.toString());
    }

    /**
     * For User to update profile when new sign up. Requires userID
     */
    @PostMapping("/insertProfile")
    public ResponseEntity<String> saveNewProfile(@RequestBody ProfileDTO updateRequest){
        if(userDetailsSvc.existsByUserId(updateRequest.getUserProfile().getUserId())){
            JsonObject response = Json.createObjectBuilder().add("Create Account Error", "UserID does not Exist").build();
            return ResponseEntity.badRequest().body(response.toString());
        }
        userDetailsSvc.saveNewProfile(updateRequest);
        JsonObject response = Json.createObjectBuilder().add("Create New Profile ","Success").build();
        return ResponseEntity.ok().body(response.toString());
    }

    @GetMapping("/profile/{userid}")
    public ResponseEntity<String> getProfile(@PathVariable Integer userId) throws JsonProcessingException {
        Optional<UserProfile> profile = userDetailsSvc.findProfileByUserId(userId);
        Optional<ContactDetails> contactDetails = userDetailsSvc.findContactsByUserId(userId);
        if(profile.isEmpty() || contactDetails.isEmpty() ){
            JsonObject response = Json.createObjectBuilder().add("Get Profile Error", "User Profile does not Exist").build();
            return ResponseEntity.badRequest().body(response.toString());
        }

        ProfileDTO response =  new ProfileDTO(userId,profile.get(),contactDetails.get());
        return ResponseEntity.ok().body(objectMapper.writeValueAsString(response));
    }

    @PostMapping("/updateProfile")
    public ResponseEntity<String> updateProfile(@RequestBody UserProfile userProfile){
        if(!userDetailsSvc.existsByUserId(userProfile.getUserId())){
            JsonObject response = Json.createObjectBuilder().add("Create Account Error", "UserID does not Exist").build();
            return ResponseEntity.badRequest().body(response.toString());
        }
        userDetailsSvc.updateProfile(userProfile);
        JsonObject response = Json.createObjectBuilder().add("Update Profile ","Success").build();
        return ResponseEntity.ok().body(response.toString());
    }


    @PostMapping("/updateContact")
    public ResponseEntity<String> updateContact(@RequestBody ContactDetails contactDetails){
        if(!userDetailsSvc.existsByUserId(contactDetails.getUserId())){
            JsonObject response = Json.createObjectBuilder().add("Create Account Error", "UserID does not Exist").build();
            return ResponseEntity.badRequest().body(response.toString());
        }
        userDetailsSvc.updateContacts(contactDetails);
        JsonObject response = Json.createObjectBuilder().add("Update Profile ","Success").build();
        return ResponseEntity.ok().body(response.toString());
    }

    @PostMapping("/signin")
    public ResponseEntity<String> loginUser(@RequestBody AuthRequest signinRequest) throws JsonProcessingException{
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(signinRequest.getUsername(), signinRequest.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        Credentials usercreds = (Credentials) authentication.getPrincipal();
        AuthResponse response = new AuthResponse(usercreds.getUserId(),jwtService.generateToken(signinRequest.getUsername()),
                authentication.getAuthorities().stream().toList().get(0).toString());

        return ResponseEntity.ok().body(objectMapper.writeValueAsString(response));

    }

    @GetMapping("/test")
    public ResponseEntity<String> testAuth(){
        Credentials cred = (Credentials) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        log.info("GET:{}",cred);
        return ResponseEntity.ok("Auth Test Success");
    }


}
