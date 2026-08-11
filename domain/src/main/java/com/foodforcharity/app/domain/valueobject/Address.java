package com.foodforcharity.app.domain.valueobject;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor // Necessário para o JPA
@AllArgsConstructor // Facilita na hora de instanciar o endereço
@Embeddable 
public class Address {

    @Column(name = "ADDRESS_DESCRIPTION")
    @NotNull
    private String addressDescription;

    @NotNull
    private String city;

    @NotNull
    private String country;
}