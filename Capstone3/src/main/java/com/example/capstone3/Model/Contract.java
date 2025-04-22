package com.example.capstone3.Model;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
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
public class Contract {


    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;


    @ManyToOne(cascade = CascadeType.ALL)
    private Owner owner;


    @OneToOne
    @PrimaryKeyJoinColumn
    private Offer offer;

    @NotEmpty(message = "Cannot be Empty")
    private String ContractDocumentationPath;

    @NotEmpty(message = "Agree Cost Cannot be Empty")
    private Integer agreeCost;

    @NotEmpty(message = "using Years Cannot be Empty")
    private Integer usingYears;

    @NotEmpty(message = "start Date Cannot be Empty")
    private LocalDate startDate;

    @NotEmpty(message = "end Date Cannot be Empty")
    private LocalDate endDate;

    private LocalDate paymentDate;

    private LocalDateTime ContractDate;
//    @Column
//    private Integer agreeCost;
//
//    @Column
//    private Integer usingYears;
//
//    @Column
//    private LocalDate startDate;
//
//    @Column
//    private LocalDate endDate;
//
//    @Column
//    private LocalDate paymentDate;

}
