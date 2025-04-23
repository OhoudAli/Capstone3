package com.example.capstone3.Repository;


import com.example.capstone3.Model.Offer;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OfferRepository extends JpaRepository<Offer,Integer> {

    Offer findOfferById(Integer id);

    List<Offer> findOfferByIdAndOfferStatusNot(Integer propertyId, String offerStatus);
}
