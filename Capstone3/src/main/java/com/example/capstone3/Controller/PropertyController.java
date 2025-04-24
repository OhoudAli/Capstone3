package com.example.capstone3.Controller;


import com.example.capstone3.Api.ApiResponse;
import com.example.capstone3.Model.Owner;
import com.example.capstone3.Model.Property;
import com.example.capstone3.Service.PropertyService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/v1/property")
@RestController
@RequiredArgsConstructor
public class PropertyController {

    private final PropertyService propertyService;

    @GetMapping("/get")
    public ResponseEntity getAllProperty(){
        return ResponseEntity.status(200).body(propertyService.getAllProperties());
    }

    @PostMapping("/add/{ownerId}")
    public ResponseEntity addProperty(@PathVariable Integer ownerId,@Valid @RequestBody Property property){
        propertyService.addPropertyByOwner(ownerId, property);
        return ResponseEntity.status(200).body(new ApiResponse("Property added successfully"));
    }


    @PutMapping("/update/{id}")
    public ResponseEntity updateProperty(@PathVariable Integer id, @Valid @RequestBody Property property){
        propertyService.updateProperty(id, property);
        return ResponseEntity.status(200).body(new ApiResponse("Property updated successfully"));
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteProperty(@PathVariable Integer id){
        propertyService.deleteProperty(id);
        return ResponseEntity.status(200).body(new ApiResponse("Property deleted successfully"));
    }

    //Taha
    @GetMapping("/title/{location}")
    public ResponseEntity getPropertyByTitle(@PathVariable String location) {
        return ResponseEntity.status(200).body(propertyService.getPropertyByLocation(location));
    }

    //Taha
    // Endpoint to get properties with no offers
    @GetMapping("/no-offers")
    public List<Property> getPropertiesWithNoOffers() {
        return propertyService.getPropertiesWithNoOffers();
    }


    @GetMapping("/get-by-status/{ownerId}/{status}")
    public List<Property> getByStatus(@PathVariable Integer ownerId,@PathVariable String status){
        return propertyService.getPropertyByStatus(ownerId, status);
    }

    //----------------------------------------------------------------

    //5
    @GetMapping("/calculate-price/{propertyId}")
    public ResponseEntity<Double> calculatePrice(@PathVariable Integer propertyId) {
        // استدعاء دالة الخدمة لحساب السعر
        double price = propertyService.calculatePropertyPrice(propertyId);


        return ResponseEntity.status(200).body(price);
    }

    //4 Duja مجموع ربحه السنوي
    @GetMapping("/owner/{ownerId}/total-annual-profit")
    public String getTotalAnnualProfit(@PathVariable Integer ownerId) {
        Owner owner = new Owner();
        owner.setId(ownerId);

        return propertyService.calculateTotalAnnualProfitFromAllProperties(owner);
    }

    //3 Duja تعرض العقارات الي بتنتهي خلال فتره (هو يدخل الرقم)
    @GetMapping("/ending/{proposedYears}")
    public ResponseEntity<?> endingSoon(@PathVariable int proposedYears) {
        List<Property> properties = propertyService.endingSoon(proposedYears);
        if (properties.isEmpty()) {
            return ResponseEntity.status(400).body("There is nothing ending by this period");
        }
        return ResponseEntity.ok(properties);
    }


    //عشان يوقف استقبال العروض2 Duja
    @PutMapping("/property/{propertyId}/stop-receiving-offers")
    public ResponseEntity stopReceivingOffers(@PathVariable Integer propertyId) {
    return ResponseEntity.status(200).body(new ApiResponse("stop receiving offers "));
    }
    //1 duja تحسب متوسط السعر لكل العروض
    @GetMapping("/property/{propertyId}/average-offer-price")
    public ResponseEntity<Double> getAverageOfferPrice(@PathVariable Integer propertyId) {
        double averagePrice = propertyService.calculateAverageOfferPrice(propertyId);
        return ResponseEntity.ok(averagePrice);
    }



}
