package com.foodforcharity.app.web.mapper;

import com.foodforcharity.app.domain.entity.Donor;
import com.foodforcharity.app.web.dto.AddressDto;
import com.foodforcharity.app.web.dto.DonorDto;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.stream.Collectors;

@Component
public class DonorMapper {

    private final FoodDtoMapper foodDtoMapper;

    // Injeção de dependência do novo Mapper via construtor (Recomendado pelo Spring)
    public DonorMapper(FoodDtoMapper foodDtoMapper) {
        this.foodDtoMapper = foodDtoMapper;
    }

    public DonorDto toDto(Donor donor) {
        if (donor == null) {
            return null;
        }

        DonorDto dto = new DonorDto();
        dto.setId(donor.getId());
        dto.setName(donor.getDonorName());
        dto.setDiscountApplied(donor.getDiscountApplied());
        dto.setDonorStatus(donor.getDonorStatus());
        dto.setEmail(donor.getEmail());

        // 1. Mapeamento do Endereço
        if (donor.getAddress() != null) {
            AddressDto addressDto = new AddressDto();
            addressDto.setAddressDescription(donor.getAddress().getAddressDescription());
            addressDto.setCity(donor.getAddress().getCity());
            addressDto.setCountry(donor.getAddress().getCountry());
            dto.setAddress(addressDto);
        }

        // 2. Mapeamento da Lista de Alimentos
        if (donor.getFoods() != null) {
            // CORREÇÃO: Utilizando o mapper recém-injetado
            dto.setFoods(donor.getFoods().stream()
                    .map(foodDtoMapper::toDto) 
                    .collect(Collectors.toList()));
        } else {
            dto.setFoods(Collections.emptyList());
        }

        // 3. Cálculo de Rating (A lógica de negócio sai do DTO e vem pro Mapper)
        Integer calculatedRating = donor.getNumberOfRating() != 0 
                ? donor.getRating() / donor.getNumberOfRating() 
                : -1;
        dto.setRating(calculatedRating);

        return dto;
    }
}