package com.example.capstone3.Service;


import com.example.capstone3.Api.ApiException;
import com.example.capstone3.Model.Investor;
import com.example.capstone3.Model.Offer;
import com.example.capstone3.Model.Property;
import com.example.capstone3.Repository.InvestorRepository;
import com.example.capstone3.Repository.OfferRepository;
import com.example.capstone3.Repository.PropertyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OfferService {


    private final InvestorRepository investorRepository;
    private final PropertyRepository propertyRepository;
    private final OfferRepository offerRepository;


    public List<Offer> getAllOffers(){
        return offerRepository.findAll();
    }

    public void addOffer(Offer offer){


        offerRepository.save(offer);
    }

    public void updateOffer(Offer offer, Integer id){
        Offer oldOffer= offerRepository.findOfferById(id);

        if (oldOffer==null){
            throw new ApiException("Offer Not found!!");
        }

        oldOffer.setOfferStatus(offer.getOfferStatus());
        oldOffer.setInvestor(offer.getInvestor());
        oldOffer.setProperty(offer.getProperty());
        oldOffer.setOfferStatus(offer.getOfferStatus());
        oldOffer.setOrderDate(offer.getOrderDate());
        oldOffer.setProposedCost(offer.getProposedCost());
        oldOffer.setProposedYears(offer.getProposedYears());

        offerRepository.save(oldOffer);
    }

    public void deleteOffer(Integer id){
        Offer offer= offerRepository.findOfferById(id);

        if(offer == null){throw new ApiException("Offer Not found!!");}
        offerRepository.delete(offer);
    }

    public void assignInvestorToOffer(Integer offerId, Integer investorId) {
        Offer offer = offerRepository.findOfferById(offerId);
        Investor investor = investorRepository.findInvestorById(investorId);

        if (offer == null) throw new ApiException("Offer not found");
        if (investor == null) throw new ApiException("Investor not found");

        offer.setInvestor(investor);
        offerRepository.save(offer);
    }

    public void assignPropertyToOffer(Integer offerId, Integer propertyId) {
        Offer offer = offerRepository.findOfferById(offerId);
        Property property = propertyRepository.findPropertiesById(propertyId);

        if (offer == null){ throw new ApiException("Offer not found");}
        if (property == null){ throw new ApiException("Property not found");}

        offer.setProperty(property);
        offerRepository.save(offer);
    }
}
