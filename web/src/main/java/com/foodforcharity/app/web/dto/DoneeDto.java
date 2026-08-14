package com.foodforcharity.app.web.dto;

import com.foodforcharity.app.domain.constant.DoneeStatus;
import com.foodforcharity.app.domain.constant.DoneeType;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DoneeDto {

    // Se for usado apenas para POST, remova o ID. Se for PUT/Response, mantenha.
    private Long id;

    @NotBlank
    private String name;

    @Valid
    @NotNull
    private AddressDto address;

    @NotBlank
    @Email
    private String email;

    @NotNull
    @PositiveOrZero
    private Integer memberCount;

    @NotBlank
    private String phoneNumber;

    @NotNull
    @PositiveOrZero
    private Integer quantityRequested;

    @NotNull
    private DoneeStatus status;

    @NotNull
    private DoneeType type;
}