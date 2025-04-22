package com.example.capstone3.Model;


import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.*;

import java.util.Set;

@Setter
@Getter
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class MunicioalRequirment {


    @Id
    private Integer id ;


    @OneToMany(cascade = CascadeType.ALL,mappedBy = "municioalrequirment")
    private Set<Property> property;
}
