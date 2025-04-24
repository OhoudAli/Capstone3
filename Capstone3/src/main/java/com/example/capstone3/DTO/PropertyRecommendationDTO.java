package com.example.capstone3.DTO;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PropertyRecommendationDTO {


    //البتول
    //توصيه بالعقارات
    private Integer propertyId;
    private String title;
    private String location;
    private String type;
    private Double areaSize;
    private String status;

}
