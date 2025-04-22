package com.example.capstone3.Model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
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

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "owner")
    private Set<Contract> contract;

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "owner")
    private Set<Property> properties;

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "owner")
    private Set<Offer> offers;




}
