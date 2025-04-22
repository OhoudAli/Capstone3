package com.example.capstone3.Model;


import jakarta.persistence.*;
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
public class Contract {


    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;


    @ManyToOne(cascade = CascadeType.ALL)
    private Owner owner;

    @OneToOne
    @PrimaryKeyJoinColumn
    private Investor investor;

    @OneToOne
    @PrimaryKeyJoinColumn
    private Offer offer;


    @Column
    private Integer agreeCost;

    @Column
    private Integer usingYears;

    @Column
    private LocalDate startDate;

    @Column
    private LocalDate endDate;

    @Column
    private LocalDate paymentDate;

}
