package com.example.capstone3.Model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

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
    @NotNull
    private Double cost;

    @Column
    @NotNull
    private Integer years;

    @Column
    @NotEmpty
    private String additionalTerm;

    @Column
    private String offerStatus;

    @Column
    private LocalDate orderDate;

    @OneToOne(mappedBy = "offer")
    @JsonIgnore
    private Contract contract;

    private LocalDateTime lastOfferTime;

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
