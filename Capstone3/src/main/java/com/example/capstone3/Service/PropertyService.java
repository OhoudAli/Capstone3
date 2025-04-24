package com.example.capstone3.Service;


import com.example.capstone3.Api.ApiException;
import com.example.capstone3.Model.Admin;
import com.example.capstone3.Model.Offer;
import com.example.capstone3.Model.Owner;
import com.example.capstone3.Model.Property;
import com.example.capstone3.Repository.AdminRepository;
import com.example.capstone3.Repository.OfferRepository;
import com.example.capstone3.Repository.OwnerRepository;
import com.example.capstone3.Repository.PropertyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PropertyService {


    private final PropertyRepository propertyRepository;
    private final OwnerRepository ownerRepository;
    private final AdminRepository adminRepository;
    private final EmailService emailService;
    private final OfferRepository offerRepository;

// Ohoud 4: if investor want to see the properties "allow to see the active properties only"
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

    //Ohoud 5: owner can search by status to find the active or inactive property
    public List<Property> getPropertyByStatus(Integer ownerId,String status){
        return propertyRepository.findPropertyByStatus(status);
    }

    //Ohoud 6: owner can insert property
    public void addPropertyByOwner(Integer ownerId,Property property){
        Owner owner = ownerRepository.findOwnerById(ownerId);
        if(owner == null){
            throw new ApiException("owner required to add property");
        }

        property.setOwner(owner);
        property.setStatus("pending");
        propertyRepository.save(property);

    }

    //Ohoud 7 : this get only for admin to see the  request for activating the properties or not
    public List<Property> getAllPropertyByAdmin(Integer adminId){
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
        //Ohoud
        //if updating property the admin should activate again
        oldProperty.setStatus("pending");


        propertyRepository.save(oldProperty);

    }



    public void deleteProperty(Integer id){
        Property property = propertyRepository.findPropertiesById(id);
        if(property == null){
            throw new ApiException("Property not found");
        }

        propertyRepository.delete(property);
    }

    //Ohoud 8: only the admin can activate the properties and if it is active the owner well receive an email
    public void activeTheProperty(Integer propertyId,Integer adminId){
        Admin  admin = adminRepository.findAdminById(adminId);
        Property property =propertyRepository.findPropertiesById(propertyId);
        if(property == null){
            throw new ApiException("Property not found");
        }
        if(admin == null){
            throw new ApiException("Admin not found");
        }

        property.setStatus("Active");
        propertyRepository.save(property);

        String messageToOwner = "Your request for property " + property.getTitle() + " has been active.";
        emailService.sendEmail(property.getOwner().getEmail(), "Property is active now", messageToOwner);
    }


    //Ohoud 9: only admin could reject if owner not follow the instructions and receive an email
    public void rejectTheProperty(Integer propertyId,Integer adminId){
        Admin admin = adminRepository.findAdminById(adminId);
        Property property =propertyRepository.findPropertiesById(propertyId);
        if(property == null){
            throw new ApiException("Property not found");
        }
        if(admin == null){
            throw new ApiException("Admin not found");
        }

        property.setStatus("Inactive");
        propertyRepository.save(property);

        String messageToOwner = "Your request for property " + property.getTitle() + " rejected for failure to follow the instructions.";
        emailService.sendEmail(property.getOwner().getEmail(), "Property is Inactive now", messageToOwner);
    }


    //Taha.10
    public List<Property>  getPropertyByLocation(String location) {
        List<Property>  properties =  propertyRepository.findPropertiesByLocation(location);
        if (properties.isEmpty()) {
            throw new ApiException("No properties found in location: " + location);
        }
        return properties;
    }

    //Taha.11
    // Method to get properties that have no offers
    //Helps the user to market the property better
    public List<Property> getPropertiesWithNoOffers() {
        List<Property> allProperties = propertyRepository.findAll(); // Get all properties
        List<Property> propertiesWithNoOffers = new ArrayList<>();

        for (Property property : allProperties) {
            if (property.getOffer().isEmpty()) { // Check if the property has no offers
                propertiesWithNoOffers.add(property); // Add it to the list if it has no offers
            }
        }

        return propertiesWithNoOffers; // Return the filtered list
    }


    //3 Duja
    public String calculateTotalAnnualProfitFromAllProperties(Owner owner) {
        List<Property> properties = propertyRepository.findByOwner(owner);
        if (properties.isEmpty()) {
            throw  new ApiException("There are no properties owned by this owner or the owner is not present");
        }
        double totalAnnualProfit = 0;

        for (Property property : properties) {
            Double annualRent = property.getAnnualRent();

            if (annualRent != null) {
                totalAnnualProfit += annualRent;
            }
        }

        return "Total owner's profit from all properties during the year: " + totalAnnualProfit + "SR";
    }



    //4 Duja
    public boolean stopReceivingOffers(Integer propertyId) {
        return propertyRepository.findById(propertyId).map(property -> {
            property.setAcceptingOffers(false);
            propertyRepository.save(property);
            return true;
        }).orElse(false);
    }


    //Duja
    //Duja
    public List<Property> endingSoon(int proposedYears) {
        int proposedDays = proposedYears * 365;
        LocalDate today = LocalDate.now();
        LocalDate endDate = today.plusDays(proposedDays);
        return propertyRepository.findPropertiesByLeaseEndDateBetween(today, endDate);
    }

    public Property findById(Integer propertyId) {
        return propertyRepository.findById(propertyId)
                .orElseThrow(() -> new ApiException("Property not found"));
    }

    //Duja
    public double calculatePropertyPrice(Integer propertyId) {
        Property property = propertyRepository.findById(propertyId)
                .orElseThrow(() -> new ApiException("Property not found"));

        double pricePerMeter = 1975.0;
        Double area = property.getAreaSize();

        if (area == null || area <= 0) {
            throw new ApiException("Invalid area size");
        }
        return area * pricePerMeter;
    }



    //6 بيحسب  متوسط العروض الي جته Duja
    public double calculateAverageOfferPrice(Integer propertyId) {
        Property property = propertyRepository.findById(propertyId)
                .orElseThrow(() -> new ApiException("Property not found"));

        List<Offer> offers = offerRepository.findByProperty_Id(propertyId);

        if (offers.isEmpty()) {
            return 0;
        }

        double totalPrice = 0;
        int validOffersCount = 0;

        for (Offer offer : offers) {
            Double cost = Double.valueOf(offer.getCost());
            if (cost != null) {
                totalPrice += cost;
                validOffersCount++;
            }
        }

        return validOffersCount == 0 ? 0 : totalPrice / validOffersCount;
    }


    //حقت واحد حسبه متوسسط السعر لكل العروض Duja
    public Property getPropertyById(Integer propertyId) {
        Optional<Property> property = propertyRepository.findById(propertyId);
        return property.orElse(null);
    }


}
