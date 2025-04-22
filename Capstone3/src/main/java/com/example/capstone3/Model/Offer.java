package com.example.capstone3.Model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Offer {

    @Id
    private Integer id;

    @OneToOne
    private Contract contract;

    @ManyToOne
    @JoinColumn(name = "offer_id",referencedColumnName = "id")
    @JsonIgnore
    private Property property;


    @ManyToOne
    @JoinColumn
    @JsonIgnore
    private Owner owner;

    @ManyToOne
    @JoinColumn
    @JsonIgnore
    private Investor investor;
}
