package com.example.capstone3.Service;


import com.example.capstone3.Api.ApiException;
import com.example.capstone3.Model.Contract;
import com.example.capstone3.Model.Offer;
import com.example.capstone3.Model.Owner;
import com.example.capstone3.Repository.ContractRepository;
import com.example.capstone3.Repository.OfferRepository;
import com.example.capstone3.Repository.OwnerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ContractService {


    private final ContractRepository contractRepository;
    private final OwnerRepository ownerRepository;
    private final OfferRepository offerRepository;
    private final EmailService emailService;

    public void addContract(Integer ownerId, Integer offerId, Contract contract) {
        Owner owner = ownerRepository.findOwnerById(ownerId);
        if (owner == null) {
            throw new ApiException("owner with id " + ownerId + " not found");
        }
        Offer offer=offerRepository.findOfferById(offerId);
        if (offer==null) {
            throw new ApiException("offer with id " + offerId + " not found");
        }
        contractRepository.save(contract);

    }
    public List<Contract> getContract () {
        return contractRepository.findAll();
    }

    public void updateContract(Integer contractId, Contract contract ) {
        Contract c = contractRepository.findContractById(contractId);
        if (c == null) {
            throw new ApiException("contract with id " + contractId + " not found");
        }
        c.setContractDocumentationPath(contract.getContractDocumentationPath());
        c.setAgreeCost(contract.getAgreeCost());
        c.setEndDate(contract.getEndDate());
        c.setPaymentDate(contract.getPaymentDate());
        c.setStartDate(contract.getStartDate());
        c.setContractDate(contract.getContractDate());
        c.setUsingYears(contract.getUsingYears());
        contractRepository.save(c);
    }

    public void deleteContract(Integer contractId) {
        Contract contract = contractRepository.findContractById(contractId);
        if(contract == null){
            throw new ApiException("Course with id " + contractId + " not found");
        }
        contractRepository.delete(contract);


    }

    //Ohoud 10: creating the contract after accept the offer
//    public void createContractFromOffer(Integer offerId) {
//        Offer offer = offerRepository.findOfferById(offerId);
//
//        if (offer == null) {
//            throw new ApiException("Offer Not found!!");
//        }
//
//        if (!offer.getOfferStatus().equals("Accepted")) {
//            throw new ApiException("Cannot create contract, offer must be accepted first!");
//        }
//
//        Contract contract = new Contract();
//        contract.setOffer(offer);
//        contract.setUsingYears(offer.getYears());
//        contract.setContractDate(LocalDateTime.now().toLocalDate().atStartOfDay());
//        contract.setStartDate(LocalDate.now());
//        contract.setEndDate(LocalDate.now().plusYears(offer.getYears()));
//        contract.setAgreeCost(offer.getCost());
//        contract.setContractDocumentationPath("contracts/offer_" + offerId + ".pdf");
//        contract.setOwner(offer.getOwner());
//        contract.setInvestor(offer.getInvestor());
//
//        contractRepository.save(contract);
//
//        String messageToInvestor = "A contract has been created for the accepted offer for property " + offer.getProperty().getTitle() + ".";
//        emailService.sendEmail(offer.getInvestor().getEmail(), "Contract Created", messageToInvestor);
//
//        String messageToOwner = "A contract has been created for the accepted offer for property " + offer.getProperty().getTitle() + ".";
//        emailService.sendEmail(offer.getOwner().getEmail(), "Contract Created", messageToOwner);
//    }


//    public String extendContract(ContractExtensionDTO dto) {
//        Contract contract = contractRepository.findContractById(dto.getContractId());
//
//        if (contract == null)
//            return "Contract not found";
//
//        if (!dto.getOwnerApproval())
//            return "Extension must be approved by Owner ";
//
//        LocalDate today = LocalDate.now();
//        LocalDate twoMonthsFromToday = today.plusMonths(2);
//
//        if (contract.getEndDate().isAfter(twoMonthsFromToday)) {
//            return "Contract is not eligible for extension. More than 2 months remaining.";
//        }
//        // تمديد عدد السنوات وتحديث التاريخ
//        contract.setUsingYears(contract.getUsingYears() + dto.getExtraYears());
//        contract.setEndDate(contract.getEndDate().plusYears(dto.getExtraYears()));
//
//        contractRepository.save(contract);
//        return "Contract extended successfully until: " + contract.getEndDate();
//
//
  }
