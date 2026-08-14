package com.foodforcharity.app.web.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddressDto {
    
    @NotBlank
    private String addressDescription;
    
    @NotBlank
    private String city;
    
    @NotBlank
    private String country;
}