package com.foodforcharity.app.web.mapper;

import com.foodforcharity.app.domain.entity.Donee;
import com.foodforcharity.app.web.dto.AddressDto;
import com.foodforcharity.app.web.dto.DoneeDto;
import org.springframework.stereotype.Component;

@Component
public class DoneeMapper {

    public DoneeDto toDto(Donee donee) {
        if (donee == null) {
            return null;
        }

        DoneeDto dto = new DoneeDto();
        dto.setId(donee.getId());
        dto.setName(donee.getDoneeName());
        dto.setEmail(donee.getEmail());
        dto.setMemberCount(donee.getMemberCount());
        dto.setQuantityRequested(donee.getQuantityRequested());
        dto.setStatus(donee.getDoneeStatus());
        dto.setType(donee.getDoneeType());
        dto.setPhoneNumber(donee.getPhoneNumber());

        if (donee.getAddress() != null) {
            AddressDto addressDto = new AddressDto();
            addressDto.setAddressDescription(donee.getAddress().getAddressDescription());
            addressDto.setCity(donee.getAddress().getCity());
            addressDto.setCountry(donee.getAddress().getCountry());
            dto.setAddress(addressDto);
        }

        return dto;
    }
}