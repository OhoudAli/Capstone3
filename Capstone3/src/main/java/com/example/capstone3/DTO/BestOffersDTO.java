package com.example.capstone3.DTO;


import com.example.capstone3.Model.Offer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BestOffersDTO {

    private Offer highestCostOffer;
    private Offer shortestYearsOffer;
}
