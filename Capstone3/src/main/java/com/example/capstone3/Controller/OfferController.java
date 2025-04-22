package com.example.capstone3.Controller;


import com.example.capstone3.Api.ApiResponse;
import com.example.capstone3.Model.Offer;
import com.example.capstone3.Service.OfferService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/offer")
public class OfferController {

    private final OfferService offerService;

    @GetMapping("/get")
    public ResponseEntity getAllOffers() {
        return ResponseEntity.status(200).body(offerService.getAllOffers());
    }

    @PostMapping("/add")
    public ResponseEntity addOffer(@Valid @RequestBody Offer offer) {
        offerService.addOffer(offer);
        return ResponseEntity.status(200).body(new ApiResponse("Offer added successfully"));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity updateOffer(@PathVariable Integer id, @Valid @RequestBody Offer offer) {
        offerService.updateOffer(offer, id);
        return ResponseEntity.status(200).body(new ApiResponse("Offer updated successfully"));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteOffer(@PathVariable Integer id) {
        offerService.deleteOffer(id);
        return ResponseEntity.status(200).body(new ApiResponse("Offer deleted successfully"));
    }

    @PutMapping("/assign-investor/{offerId}/to/{investorId}")
    public ResponseEntity assignInvestorToOffer(@PathVariable Integer offerId, @PathVariable Integer investorId) {
        offerService.assignInvestorToOffer(offerId, investorId);
        return ResponseEntity.status(200).body(new ApiResponse("Investor assigned to offer successfully"));
    }

    @PutMapping("/assign-property/{offerId}/to/{propertyId}")
    public ResponseEntity assignPropertyToOffer(@PathVariable Integer offerId, @PathVariable Integer propertyId) {
        offerService.assignPropertyToOffer(offerId, propertyId);
        return ResponseEntity.status(200).body(new ApiResponse("Property assigned to offer successfully"));
    }
}
