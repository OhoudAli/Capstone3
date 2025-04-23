package com.example.capstone3.Service;


import com.example.capstone3.Api.ApiException;
import com.example.capstone3.Model.Owner;
import com.example.capstone3.Model.Property;
import com.example.capstone3.Repository.OwnerRepository;
import com.example.capstone3.Repository.PropertyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PropertyService {


    private final PropertyRepository propertyRepository;
    private final OwnerRepository ownerRepository;


    public List<Property> getAllProperties(){
        return propertyRepository.findAll();
    }

    public void addProperty(Property property){
        Owner owner = ownerRepository.findOwnerById(property.getId());
        if(owner == null){
            throw new ApiException("owner required to add property");
        }

        propertyRepository.save(property);

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
}
