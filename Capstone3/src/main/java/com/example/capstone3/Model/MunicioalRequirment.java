package com.example.capstone3.Model;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

import java.util.Set;

@Setter
@Getter
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class MunicioalRequirment {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id ;


    @OneToMany(cascade = CascadeType.ALL,mappedBy = "municioalrequirment")
    private Set<Property> property;

    @Column
    @NotEmpty
    private String applicabTo;

    @Column
    @NotEmpty
    private String requirementDetails;


}
