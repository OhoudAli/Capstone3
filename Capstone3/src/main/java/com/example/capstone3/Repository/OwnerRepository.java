package com.example.capstone3.Repository;

import com.example.capstone3.Model.Offer;
import com.example.capstone3.Model.Owner;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OwnerRepository extends JpaRepository<Owner,Integer> {

    Owner findOwnerById(Integer id);
//
//    Owner countByInvestorIdAndOfferStatus(Integer investorId, String offerStatus);
//
//    Owner countById(Integer id);
//
//    Owner countByOwnerIdAndOfferStatus(int ownerId, String accepted);
//
//    List<Offer> findOfferByIdAndOfferStatusNot(Integer id, String accepted);
//
//    List<Offer> findByPropertyId(Integer propertyId);
//    // استعلام للحصول على آخر عرض للمستثمر
//    Offer findTopByInvestorIdOrderByLastOfferTimeDesc(Integer investorId);

}
