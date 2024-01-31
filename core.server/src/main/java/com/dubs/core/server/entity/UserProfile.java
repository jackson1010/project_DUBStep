 package com.dubs.core.server.entity;

 import com.dubs.core.server.enums.BloodType;
 import com.dubs.core.server.enums.Gender;
 import jakarta.persistence.*;
 import jakarta.validation.constraints.Max;
 import jakarta.validation.constraints.Min;
 import lombok.AllArgsConstructor;
 import lombok.Getter;
 import lombok.NoArgsConstructor;
 import lombok.Setter;
 import lombok.ToString;

 import java.util.Date;

 @Entity
 @Table(name="user_profiles")
 @Getter
 @Setter
 @ToString
 @NoArgsConstructor
 @AllArgsConstructor
 public class UserProfile {


     @Id
     @GeneratedValue(strategy = GenerationType.IDENTITY)
     @Max(65535)
     @Min(0)
     private Integer id;

     @Max(65535)
     @Min(0)
     private Integer userId;

     private String firstName;
     private String lastName;

     private Date dateOfBirth;

     @Enumerated(EnumType.STRING)
     private Gender gender;

     private Double height;
     private Double weight;

     @Enumerated(EnumType.STRING)
     private BloodType bloodType;

//     Medications that affect heart rate
//     Max HR
//     Training Background
//     Preferred Sleep Time
//     Activity Goal
//     Fitzpatrick Skin Type

 }
