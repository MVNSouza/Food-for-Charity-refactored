package com.foodforcharity.app.domain.valueobject;

import lombok.Data;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

@Data
@Embeddable // Diz ao Hibernate para colocar essas colunas na tabela de quem usar essa classe
public class Address {

    @Column(name = "ADDRESS_DESCRIPTION")
    @NotNull
    private String addressDescription;

    @NotNull
    private String city;

    @NotNull
    private String country;
}