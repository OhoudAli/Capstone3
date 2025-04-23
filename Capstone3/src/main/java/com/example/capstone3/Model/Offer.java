package com.example.capstone3.Model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Offer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;


    @Column
    private Double cost;

    @Column

    private Integer years;

    @Column
    private String additionalTerm;

    @Column
    private String offerStatus;

    @Column
    private LocalDate orderDate;

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
