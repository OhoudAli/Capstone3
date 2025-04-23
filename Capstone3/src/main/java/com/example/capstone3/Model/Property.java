package com.example.capstone3.Model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDate;
import java.util.Set;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Property {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;


    @Column
    @NotEmpty
    private String title;


    @NotEmpty
    @Column
    private String description;

    @Column
    @NotEmpty
    private String type;

    @NotEmpty
    @Column
    private String location;


    @NotEmpty
    @Column
    private Double areaSize;

    @NotEmpty
    @Column
    private LocalDate CreateAt;

    @Column
    @NotEmpty
    @Pattern(regexp = "Active|Inactive")
    private String status;



    @OneToMany(cascade = CascadeType.ALL,mappedBy = "property")
    private Set<Offer> offer;

    @ManyToOne
    @JsonIgnore
    @JoinColumn
    private Owner owner;



}
