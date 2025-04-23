package com.example.capstone3.Controller;


import com.example.capstone3.Api.ApiResponse;
import com.example.capstone3.Model.Property;
import com.example.capstone3.Service.PropertyService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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


//    @PutMapping("/activeProperty/{propertyId}/{adminId}")
//    public ResponseEntity activeTheProperty(@PathVariable Integer propertyId, @PathVariable Integer adminId){
//        propertyService.activeTheProperty(propertyId, adminId);
//        return ResponseEntity.status(200).body(new ApiResponse("Property is now active and visible to investors"));
//    }
//
//    @PutMapping("/rejectProperty/{propertyId}/{adminId}")
//    public ResponseEntity rejectTheProperty(@PathVariable Integer propertyId, @PathVariable Integer adminId) {
//        propertyService.rejectTheProperty(propertyId, adminId);
//        return ResponseEntity.status(200).body(new ApiResponse("Property is now active and visible to investorsProperty has been rejected and hidden from investors"));
//
//    }
}
