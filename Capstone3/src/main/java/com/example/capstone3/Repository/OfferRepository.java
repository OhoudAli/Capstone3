package com.example.capstone3.Repository;


import com.example.capstone3.Model.Offer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OfferRepository extends JpaRepository<Offer,Integer> {

    Offer findOfferById(Integer id);
}
