package com.example.capstone3.DTO;


import com.example.capstone3.Model.Offer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BestOfferDTO {

    private Integer offerId;
    private Integer proposedCost;
    private Integer proposedYears;
    private String additionalTerm;
    private String offerStatus;
    private LocalDate orderDate;
    private Integer investorId;


}
