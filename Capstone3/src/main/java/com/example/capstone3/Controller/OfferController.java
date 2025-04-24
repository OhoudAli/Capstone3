package com.example.capstone3.Controller;


import com.example.capstone3.Api.ApiResponse;
import com.example.capstone3.DTO.BestOffersDTO;
import com.example.capstone3.Model.Offer;
import com.example.capstone3.Service.OfferService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.Map;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/offer")
public class OfferController {

    private final OfferService offerService;

    @GetMapping("/get")
    public ResponseEntity getAllOffers() {
        return ResponseEntity.status(200).body(offerService.getAllOffers());
    }

    @PostMapping("/addOfferByInvestor/{investorId}/{propertyId}")
    public ResponseEntity addOffer(@PathVariable Integer investorId,@PathVariable Integer propertyId,@Valid @RequestBody Offer offer) {
        offerService.addOfferByInvestor(investorId,propertyId, offer);
        return ResponseEntity.status(200).body(new ApiResponse("Offer posted successfully"));
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteOffer(@PathVariable Integer id) {
        offerService.deleteOffer(id);
        return ResponseEntity.status(200).body(new ApiResponse("Offer deleted successfully"));
    }



    //--------------------------------------------------
    //Taha
    // Get the offer with the highest cost
    @GetMapping("/highest-cost")
    public Offer getHighestCostOffer() {
        return offerService.getHighestCostOffer();
    }

    // Get the offer with the least years
    @GetMapping("/least-years")
    public Offer getOfferWithLeastYears() {
        return offerService.getOfferWithLeastYears();
    }

    // ---
    @GetMapping("/compare-best-offers")
    public ResponseEntity<BestOffersDTO> compareBestOffers() {
        BestOffersDTO result = offerService.getBestOffersComparison();
        return ResponseEntity.ok(result);
    }

    @GetMapping("/property/{propertyId}/offer-count")
    public ResponseEntity<?> getOffersCountForProperty(@PathVariable Integer propertyId) {
        Integer count = offerService.getOffersCountForProperty(propertyId);
        return ResponseEntity.ok(Collections.singletonMap("offersCount", count));
    }

    //Taha
    @GetMapping("/location-count")
    public Map<String, Integer> getOffersCountPerLocation() {
        return offerService.getOffersCountPerLocation();
    }


    //7 Duja مانخلي التاجر يسوي سبميت لاكثر من اوفر بنفس الدقيقه
    @PostMapping("/submit")
    public ResponseEntity submitOffer(@RequestParam Integer propertyId, @RequestParam Integer investorId, @RequestParam Integer price) {

        Offer newOffer = offerService.submitOffer(propertyId, investorId, price);
        return ResponseEntity.status(200).body(newOffer);

    }

}
