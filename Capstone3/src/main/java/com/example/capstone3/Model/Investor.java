package com.example.capstone3.Model;


import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Investor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotEmpty(message = "name cant be empty")
    @Size(min = 3,message = "the name length should be 5 letters at lest ")
    private String name;


    @Email
    @Column(unique = true)
    private String email;

    @NotEmpty(message = "password cant be empty")
    @Column
    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$", message = "password must contain letters ,digits and special character")
    private String password;

    @Column
    @NotEmpty(message = "phone number is required")
    @Pattern(regexp = "^05\\d{8}$", message = "Phone number must start with 0 and be exactly 10 digits")
    private String phone_number;


//    @OneToOne
//    private Contract contract;


    @OneToMany(cascade = CascadeType.ALL,mappedBy = "investor")
    private Set<Offer> offer;

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "investor")
    private Set<Contract> contracts;


}
