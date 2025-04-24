package com.example.capstone3.DTO;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InvestorPreferencesDTO {


    private List<String> preferredType;
    private List<String> preferredLocation;
    private List<Double> preferredAreas;
}
