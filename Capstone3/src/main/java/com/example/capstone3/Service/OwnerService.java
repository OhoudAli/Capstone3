package com.example.capstone3.Service;


import com.example.capstone3.Api.ApiException;
import com.example.capstone3.Api.ApiResponse;
import com.example.capstone3.DTO.OwnerOfferCountDTO;
import com.example.capstone3.Model.Owner;
import com.example.capstone3.Repository.OfferRepository;
import com.example.capstone3.Repository.OwnerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OwnerService {

    private final OwnerRepository ownerRepository;
    private final OfferRepository offerRepository;

    public List<Owner> getAllOwners() {
        return ownerRepository.findAll();
    }

    public Owner getById(Integer id) {
        if(id == null){
            throw new ApiException("Owner not found");
        }
        return ownerRepository.findOwnerById(id);
    }

    public void addOwner(Owner owner) {
        ownerRepository.save(owner);
    }

    public void updateOwner(Integer id, Owner owner) {
        Owner oldOwner = ownerRepository.findOwnerById(id);
        if(oldOwner == null){
            throw new ApiException("Owner not found");
        }
        oldOwner.setName(owner.getName());
        oldOwner.setEmail(owner.getEmail());
        oldOwner.setPassword(owner.getPassword());
        oldOwner.setPhone_number(owner.getPhone_number());

        ownerRepository.save(owner);
    }

    public void deleteOwner(Integer id) {
        Owner owner = ownerRepository.findOwnerById(id);
        if (owner == null) {
             new ApiResponse("Owner not found");
        }
        ownerRepository.delete(owner);
    }

    //Taha.9
    // Method to get number of offers for each owner
    //it is help to find how many offer he hase
    public List<OwnerOfferCountDTO> getNumberOfOffersForOwners() {
        List<Owner> owners = ownerRepository.findAll(); // Get all owners
        return owners.stream().map(owner -> new OwnerOfferCountDTO(owner.getId(), owner.getName(), offerRepository.countByOwner(owner).intValue())).toList();
    }


    //----------------------------------------------------------------
    //Duja
//    public String calculateOfferAcceptanceRate(int ownerId) {
//        int totalOffers = offerRepository.countByOwnerId(ownerId);
//
//        if (totalOffers == 0) {
//            return "Owner has no offers.";
//        }
//
//        int acceptedOffers = offerRepository.countByOwnerIdAndOfferStatus(ownerId, "Accepted");
//        double rate = (double) acceptedOffers / totalOffers * 100;
//
//        return "Owner's bid acceptance rate: " + rate + "%";
//    }
}
