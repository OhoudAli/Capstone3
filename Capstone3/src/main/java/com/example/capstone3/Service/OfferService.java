package com.example.capstone3.Service;


import com.example.capstone3.Api.ApiException;
import com.example.capstone3.DTO.BestOffersDTO;
import com.example.capstone3.Model.*;
import com.example.capstone3.Repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class OfferService {


    private final InvestorRepository investorRepository;
    private final PropertyRepository propertyRepository;
    private final OfferRepository offerRepository;
    private final OwnerRepository ownerRepository;
    private final ContractRepository contractRepository;
    private final EmailService emailService;


    public List<Offer> getAllOffers(){
        return offerRepository.findAll();
        
    }

    //Ohoud 1: add offers by investor ,if owner accept the offer the contract shall be created directly
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

    //Ohood 2: if owner accept the offer the other offers from other investor well be rejected
    //
    //Taha.2: Contract dirctly created
    public void acceptOffer(Integer ownerId,Integer offerId){
        Owner owner = ownerRepository.findOwnerById(ownerId);
        Offer offer = offerRepository.findOfferById(offerId);
        if(offer == null){
            throw new ApiException("Offer Not found!!");
        }
        if(owner == null){
            throw new ApiException("Owner Not found!!");
        }
        offer.setOfferStatus("Accepted");

        Contract contract = new Contract();
        contract.setOffer(offer);
        contract.setUsingYears(offer.getYears());
        contract.setContractDate(LocalDateTime.now().toLocalDate().atStartOfDay());
        contract.setStartDate(LocalDate.now());
        contract.setEndDate(LocalDate.now().plusYears(offer.getYears()));
        contract.setAgreeCost(offer.getCost());
        contract.setContractDocumentationPath("contracts/offer_" + offerId + ".pdf");
        contract.setOwner(owner);
        contract.setInvestor(offer.getInvestor());


        contractRepository.save(contract);
        offerRepository.save(offer);


        String messageToInvestor = "Your offer for property " + offer.getProperty().getTitle() + " has been accepted, and a contract has been created.";
        emailService.sendEmail(offer.getInvestor().getEmail(), "Offer Accepted & Contract Created", messageToInvestor);

        String messageToOwner = "Your offer for property " + offer.getProperty().getTitle() + " has been accepted, and a contract has been created.";
        emailService.sendEmail(owner.getEmail(), "Offer Accepted & Contract Created", messageToOwner);

        Property property = offer.getProperty();
        List<Offer> allOffers =offerRepository.findByProperty_IdAndOfferStatusNot(property.getId(), "Accepted");
        for(Offer o : allOffers){
            o.setOfferStatus("Rejected");
            offerRepository.save(o);
        }
    }

    //Ohoud 3: if the offer is not suitable for the owner ,owner can rejected
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

    //Taha.3
    // Get the highest cost offer
    public Offer getHighestCostOffer() {
        List<Offer> offers = offerRepository.findTopOfferByCost();
        return offers.isEmpty() ? null : offers.get(0); // Return the highest offer
    }

    //Taha.4
    // Get the offer with the least years
    public Offer getOfferWithLeastYears() {
        List<Offer> offers = offerRepository.findTopOfferByYears();
        return offers.isEmpty() ? null : offers.get(0); // Return the offer with the least years
    }

    //Taha.5
    //get best Cost and beat years
    public BestOffersDTO getBestOffersComparison() {
        Offer highestCost = offerRepository.findTopByOrderByCostDesc();
        Offer shortestYears = offerRepository.findTopByOrderByYearsAsc();

        return new BestOffersDTO(highestCost, shortestYears);
    }

//
//    //Taha.6
//    // get how many of offers in
//    public List<Map<String, Object>> getOffersCountPerProperty() {
//        List<Object[]> results = offerRepository.countOffersPerProperty();
//        List<Map<String, Object>> response = new ArrayList<>();
//        for (Object[] row : results) {
//            Map<String, Object> item = new HashMap<>();
//            item.put("propertyId", row[0]);
//            item.put("offersCount", row[1]);
//            response.add(item);
//        }
//        return response;
//    }

    //Taha.7
    // help you to know how many offer you hava in property
    public Integer getOffersCountForProperty(Integer propertyId) {
        Property property = propertyRepository.findPropertiesById(propertyId);
        if (property == null) {
            throw new ApiException("Property not found");
        }
        return offerRepository.countOffersByPropertyId(propertyId);
    }


    //Taha.8
    //Helps me find the top real estate area to focus on, like for development.
    public Map<String, Integer> getOffersCountPerLocation() {
        List<Offer> offers = offerRepository.findAll();
        Map<String, Integer> countMap = new HashMap<>();

        for (Offer offer : offers) {
            if (offer.getProperty() != null) {
                String location = offer.getProperty().getLocation();
                countMap.put(location, countMap.getOrDefault(location, 0) + 1);
            }
        }

        return countMap;
    }
}
