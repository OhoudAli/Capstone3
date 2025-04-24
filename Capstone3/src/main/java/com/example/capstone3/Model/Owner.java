package com.example.capstone3.Model;


import com.fasterxml.jackson.annotation.JsonIgnore;
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
public class Owner {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotEmpty(message="name cant be empty")
    @Column
    private String name;

    @Column (unique=true)
    @Email
    private String email;

    @NotEmpty(message="password cant be empty")
    @Column
    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$", message = "password must contain letters ,digits and special character")
    private String password;

    @NotEmpty(message="phone number can't be empty")
    @Column
    @Pattern(regexp = "^05\\d{8}$", message = "Phone number must start with 0 and be exactly 10 digits")
    private String phone_number;

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "owner")
    private Set<Contract> contract;

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "owner")
    private Set<Property> properties;

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "owner")
    private Set<Offer> offers;




}
