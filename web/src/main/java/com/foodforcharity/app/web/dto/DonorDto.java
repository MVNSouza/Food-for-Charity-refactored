package com.foodforcharity.app.web.dto;

import com.foodforcharity.app.domain.constant.DonorStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DonorDto {

    private long id;
    private String name;
    
    // Usando o AddressDto que você já criou na outra refatoração!
    private AddressDto address; 

    private String email;
    private Integer rating;
    private Integer discountApplied;
    private DonorStatus donorStatus;
    
    private List<FoodDto> foods;
}