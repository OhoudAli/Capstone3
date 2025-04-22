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

    @PostMapping("/add")
    public ResponseEntity addProperty(@Valid @RequestBody Property property){
        propertyService.addProperty(property);
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



}
