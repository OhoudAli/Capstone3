package com.example.capstone3.Model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
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
    @Size(min=8, message = "password length cant be less than 8 letters")
    @Column
    private String password;

    @NotEmpty(message="phone number can't be empty")
    @Size(min=9)
    @Column
    private String phone_number;

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "owner")
    private Set<Contract> contract;

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "owner")
    private Set<Property> properties;

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "owner")
    private Set<Offer> offers;




}
