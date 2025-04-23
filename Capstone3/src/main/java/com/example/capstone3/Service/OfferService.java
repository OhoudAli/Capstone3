package com.example.capstone3.Service;


import com.example.capstone3.Api.ApiException;
import com.example.capstone3.Model.*;
import com.example.capstone3.Repository.InvestorRepository;
import com.example.capstone3.Repository.OfferRepository;
import com.example.capstone3.Repository.OwnerRepository;
import com.example.capstone3.Repository.PropertyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OfferService {


    private final InvestorRepository investorRepository;
    private final PropertyRepository propertyRepository;
    private final OfferRepository offerRepository;
    private final OwnerRepository ownerRepository;


    public List<Offer> getAllOffers(){
        return offerRepository.findAll();
        
    }

    public void addOfferByInvestor(Integer investorId,Integer propertyId,Offer offer){
        Investor investor = investorRepository.findInvestorById(investorId);
        Property property = propertyRepository.findPropertiesById(propertyId);
        if (investor == null) {
            throw new ApiException("Investor not found");
        }
        if (property == null ) {
            throw new ApiException("Property not found");
        }
        if(!property.getStatus().equals("Active")){
            throw new ApiException("Property not active yet");
        }
         offer.setInvestor(investor);
        offer.setProperty(property);
        offer.setOfferStatus("pending");
        offer.setOrderDate(LocalDate.now());
        offerRepository.save(offer);
    }

//    public void updateOffer(Offer offer, Integer id){
//        Offer oldOffer= offerRepository.findOfferById(id);
//
//        if (oldOffer==null){
//            throw new ApiException("Offer Not found!!");
//        }
//
//        oldOffer.setOfferStatus(offer.getOfferStatus());
//        oldOffer.setInvestor(offer.getInvestor());
//        oldOffer.setProperty(offer.getProperty());
//        oldOffer.setOfferStatus(offer.getOfferStatus());
//        oldOffer.setOrderDate(offer.getOrderDate());
//        oldOffer.setProposedCost(offer.getProposedCost());
//        oldOffer.setProposedYears(offer.getProposedYears());
//
//        offerRepository.save(oldOffer);
//    }

    public void deleteOffer(Integer id){
        Offer offer= offerRepository.findOfferById(id);

        if(offer == null){throw new ApiException("Offer Not found!!");}
        offerRepository.delete(offer);
    }

    public void acceptOffer(Integer ownerId,Integer offerId){
        Owner owner = ownerRepository.findOwnerById(ownerId);
        Offer offer = offerRepository.findOfferById(offerId);
        Contract contract = new Contract();
        if(offer == null){
            throw new ApiException("Offer Not found!!");
        }
        if(owner == null){
            throw new ApiException("Owner Not found!!");
        }
        offer.setOfferStatus("Accepted");
        offer.setContract(contract);
        offerRepository.save(offer);
        

        Property property = offer.getProperty();
        List<Offer> allOffers =offerRepository.findOfferByIdAndOfferStatusNot(property.getId(), "Accepted");
        for(Offer o : allOffers){
            o.setOfferStatus("Rejected");
            offerRepository.save(o);
        }
    }

    public void rejectOffer(Integer ownerId,Integer offerId){
        Owner owner = ownerRepository.findOwnerById(ownerId);
        Offer offer = offerRepository.findOfferById(offerId);
        if(offer == null){
            throw new ApiException("Offer Not found!!");
        }
        if(owner == null){
            throw new ApiException("Owner Not found!!");
        }
        offer.setOfferStatus("Rejected");
        offerRepository.save(offer);
    }

    
}
