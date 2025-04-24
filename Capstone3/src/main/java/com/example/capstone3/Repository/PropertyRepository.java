package com.example.capstone3.Repository;

import com.example.capstone3.Model.Owner;
import com.example.capstone3.Model.Property;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface PropertyRepository extends JpaRepository<Property,Integer> {


    Property findPropertiesById(Integer id);

    //Ohoud
    List<Property> findPropertyByStatus(String status);

    List<Property> findPropertiesByLocation( String location);

    List<Property> findPropertiesByLeaseEndDateBetween(LocalDate startDate, LocalDate endDate);

    List<Property> findByOwner(Owner owner);

    List<Property> findPropertyById(Integer id);


}
