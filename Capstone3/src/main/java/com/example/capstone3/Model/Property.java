package com.example.capstone3.Model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
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
    @NotEmpty(message = "title should not be empty")
    private String title;


    @NotEmpty(message = "description should not be empty")
    @Column
    private String description;

    @Column
    @NotEmpty(message = "type should not be empty")
    private String type;

    @NotEmpty(message = "location should not be empty")
    @Column
    private String location;


    @NotNull(message = "area size should not be empty")
    @Column
    private Double areaSize;


    @Column
    private String status;

    @Column
    @NotEmpty
    @Pattern(regexp = "^S\\d{10}$")
    private String serialNumber;

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "property")
    private Set<Offer> offer;

    @ManyToOne
    @JsonIgnore
    @JoinColumn
    private Owner owner;



}
