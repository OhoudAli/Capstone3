package com.example.capstone3.Service;


import com.example.capstone3.Api.ApiException;
import com.example.capstone3.Model.Admin;
import com.example.capstone3.Model.Owner;
import com.example.capstone3.Model.Property;
import com.example.capstone3.Repository.AdminRepository;
import com.example.capstone3.Repository.OwnerRepository;
import com.example.capstone3.Repository.PropertyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PropertyService {


    private final PropertyRepository propertyRepository;
    private final OwnerRepository ownerRepository;
    private final AdminRepository adminRepository;


    public List<Property> getAllProperties(){
        List<Property> properties = propertyRepository.findAll();
        List<Property> activeProperties = new ArrayList<>();
        for (Property p: properties){
            if("Active".equals(p.getStatus())){
               activeProperties.add(p);
            }
        }

        return activeProperties;
    }

    //Ohoud : to find the active or inactive property
    public List<Property> getPropertyByStatus(String status){
        return propertyRepository.findPropertyByStatus(status);
    }

    //Worked:Ohoud
    public void addPropertyByOwner(Integer ownerId,Property property){
        Owner owner = ownerRepository.findOwnerById(ownerId);
        if(owner == null){
            throw new ApiException("owner required to add property");
        }

        property.setOwner(owner);
        property.setStatus("pending");
        propertyRepository.save(property);

    }

    //Worked:Ohoud
    public List<Property> getAllPropertyByAdmin(){
        return propertyRepository.findAll();
    }


    public void updateProperty(Integer id, Property property){
        Property oldProperty = propertyRepository.findPropertiesById(id);
        if(oldProperty == null){
            throw new ApiException("Property not found");
        }
        oldProperty.setAreaSize(property.getAreaSize());
        oldProperty.setDescription(property.getDescription());
        oldProperty.setLocation(property.getLocation());
        oldProperty.setType(property.getType());
        oldProperty.setTitle(property.getTitle());

        propertyRepository.save(oldProperty);
    }



    public void deleteProperty(Integer id){
        Property property = propertyRepository.findPropertiesById(id);
        if(property == null){
            throw new ApiException("Property not found");
        }

        propertyRepository.delete(property);
    }

    //Worked: Ohoud
    public void activeTheProperty(Integer propertyId,Integer adminId){
        Admin  admin = adminRepository.findAdminById(adminId);
        Property property =propertyRepository.findPropertiesById(propertyId);
        if(property == null){
            throw new ApiException("Property not found");
        }
        if(admin == null){
            throw new ApiException("Admin not found");
        }
//        if(property.getStatus().equals("Inactive")){
//            throw new ApiException("Property already Inactive");
//        }

        property.setStatus("Active");
        propertyRepository.save(property);

    }

    public void rejectTheProperty(Integer propertyId,Integer adminId){
        Admin admin = adminRepository.findAdminById(adminId);
        Property property =propertyRepository.findPropertiesById(propertyId);
        if(property == null){
            throw new ApiException("Property not found");
        }
        if(admin == null){
            throw new ApiException("Admin not found");
        }
//           if(property.getStatus().equals("Active")){
//               throw new ApiException("Property already active");
//           }
        property.setStatus("Inactive");
        propertyRepository.save(property);
    }

}
