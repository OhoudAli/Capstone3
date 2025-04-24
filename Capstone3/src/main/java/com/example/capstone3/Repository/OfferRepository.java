package com.example.capstone3.Repository;


import com.example.capstone3.Model.Offer;
import com.example.capstone3.Model.Owner;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OfferRepository extends JpaRepository<Offer,Integer> {

    Offer findOfferById(Integer id);

    List<Offer> findOfferByIdAndOfferStatusNot(Integer propertyId, String offerStatus);

    List<Offer> findByProperty_IdAndOfferStatusNot(Integer propertyId, String offerStatus);

    // Get the highest offer cost
    @Query("SELECT o FROM Offer o ORDER BY o.cost DESC")
    List<Offer> findTopOfferByCost();

    // Get the least offer years
    @Query("SELECT o FROM Offer o ORDER BY o.years ASC")
    List<Offer> findTopOfferByYears();

    Offer findTopByOrderByCostDesc();
    Offer findTopByOrderByYearsAsc();

    @Query("SELECT o.property.id, COUNT(o) FROM Offer o GROUP BY o.property.id")
    List<Object[]> countOffersPerProperty();

    // Used @Param to bind method parameter to named JPQL query parameter
    @Query("SELECT COUNT(o) FROM Offer o WHERE o.property.id = :propertyId")
    Integer countOffersByPropertyId(@Param("propertyId") Integer propertyId);

    // Method to count offers by owner
    Integer countByOwner(Owner owner);

    List<Offer> findByProperty_Id(Integer propertyId);

    int countByInvestorIdAndOfferStatus(Integer investorId, String offerStatus);  // تعديل هذا السطر

    int countByOwnerIdAndOfferStatus(int ownerId, String accepted);

    Offer findTopByInvestorIdOrderByLastOfferTimeDesc(Integer investorId);

    Offer countByOwnerId(Integer id);
}
