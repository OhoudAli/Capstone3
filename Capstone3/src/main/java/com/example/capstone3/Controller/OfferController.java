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



}
