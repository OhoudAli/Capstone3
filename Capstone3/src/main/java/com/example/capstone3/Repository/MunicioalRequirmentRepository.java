package com.example.capstone3.Repository;


import com.example.capstone3.Model.MunicioalRequirment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MunicioalRequirmentRepository extends JpaRepository<MunicioalRequirment,Integer> {

    MunicioalRequirment findMunicioalRequirmentById(Integer id);
    
}
