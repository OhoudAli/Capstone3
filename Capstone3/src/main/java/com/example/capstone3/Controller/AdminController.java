package com.example.capstone3.Controller;


import com.example.capstone3.Api.ApiResponse;
import com.example.capstone3.Model.Admin;
import com.example.capstone3.Service.AdminService;
import com.example.capstone3.Service.PropertyService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mapping.model.Property;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/admin")
public class AdminController {

    private final AdminService adminService;
    private final PropertyService propertyService;


    @GetMapping("/get/{id}")
    public ResponseEntity getAdmin(@PathVariable Integer id){
        return ResponseEntity.status(200).body(adminService.getAllAdmin(id));
    }


    @PostMapping("/add")
    public ResponseEntity addAdmin(@Valid @RequestBody Admin admin){
        adminService.addAdmin(admin);
        return ResponseEntity.status(200).body(new ApiResponse("Admin added successfully"));
    }


    @PutMapping("/update/{id}")
    public ResponseEntity updateAdmin(@PathVariable Integer id, @Valid @RequestBody Admin admin){
        adminService.updateAdmin(id, admin);
        return ResponseEntity.status(200).body(new ApiResponse("Admin updated successfully"));

    }

    @PutMapping("/activate/{propertyId}/{adminId}")
    public ResponseEntity activateTheProperty(@PathVariable Integer propertyId,@PathVariable Integer adminId){
        propertyService.activeTheProperty(propertyId, adminId);
        return ResponseEntity.status(200).body(new ApiResponse("Property is now active and visible to investors"));
    }

    @PutMapping("/rejectProperty/{propertyId}/{adminId}")
    public ResponseEntity rejectTheProperty(@PathVariable Integer propertyId, @PathVariable Integer adminId) {
        propertyService.rejectTheProperty(propertyId, adminId);
        return ResponseEntity.status(200).body(new ApiResponse("Property has been rejected and hidden from investors"));

    }

    @GetMapping("/get-property")
    public ResponseEntity getProperty(){
        return ResponseEntity.status(200).body(propertyService.getAllPropertyByAdmin());
    }

}
