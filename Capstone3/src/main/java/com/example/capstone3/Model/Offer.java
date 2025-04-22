package com.example.capstone3.Model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
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
    private Integer id;


    @Column
    @NotEmpty
    @Positive
    private Integer proposedCost;

    @Column
    @NotEmpty
    @Positive
    private Integer proposedYears;

    @Column
    @NotEmpty
    @Positive
    private String additionalTerm;

    @Column
    @NotEmpty
    @Pattern(regexp = "Acceptied|Unacceeptable")
    private String offerStatus;

    @Column
    @NotEmpty
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
