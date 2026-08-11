package com.foodforcharity.app.domain.entity;

import com.foodforcharity.app.domain.constant.*;
import com.foodforcharity.app.domain.valueobject.Address;
import com.foodforcharity.app.domain.valueobject.DietaryPreferences;
import lombok.Data;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.util.*;

@Data
@Entity
@DiscriminatorValue("Donee")
public class Donee extends Person {
    private static final long serialVersionUID = 1L;

    // --- Dados de Contato e Identificação ---
    @Column(name = "DONEE_NAME")
    @NotNull
    private String doneeName;

    @NotNull
    @Email
    @Column(unique = true)
    private String email;

    @Column(name = "PHONE_NUMBER")
    @NotNull
    private String phoneNumber;

    @Embedded
    private Address address;

    // --- Dados de Regra de Negócio (Doação) ---
    @Column(name = "MEMBER_COUNT")
    @NotNull
    private Integer memberCount;

    @Column(name = "QUANTITY_REQUESTED")
    @NotNull
    private Integer quantityRequested;

    @JoinColumn(name = "DONEE_STATUS")
    @Enumerated(EnumType.STRING)
    @NotNull
    private DoneeStatus doneeStatus;

    @JoinColumn(name = "DONEE_TYPE")
    @Enumerated(EnumType.STRING)
    @NotNull
    private DoneeType doneeType;

    // --- Preferências Mapeadas do JPA ---
    @OneToOne(mappedBy = "donee", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    private DoneePriceRange priceRange;

    @OneToOne(mappedBy = "donee", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    private DoneeSpiceRange spiceRange;

    // REFATORAÇÃO: Novo Objeto de Valor contendo as listas alimentares!
    @Embedded
    private DietaryPreferences dietaryPreferences = new DietaryPreferences();

    // --- Relacionamentos ---
    @OneToMany(mappedBy = "donee", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    @Fetch(value = FetchMode.SUBSELECT)
    private List<Request> requests = new ArrayList<>();

    public Donee() {
    }

    // --------------------------------------------------------
    // Métodos de Gerenciamento de Relacionamentos
    // --------------------------------------------------------

    public Request addRequest(Request request) {
        getRequests().add(request);
        request.setDonee(this);
        return request;
    }

    public void removeRequest(Request request) {
        getRequests().remove(request);
    }

    public Optional<String> getStatus() {
        return Optional.of(doneeStatus.name());
    }

    public void setSpiceRange(DoneeSpiceRange spiceRange){
        this.spiceRange = spiceRange;
        spiceRange.setDonee(this);
    }

    public void setPriceRange(DoneePriceRange priceRange){
        this.priceRange = priceRange;
        priceRange.setDonee(this);
    }

    // --------------------------------------------------------
    // Métodos de Regra de Negócio (Tell, Don't Ask)
    // --------------------------------------------------------

    public boolean isEligibleForRequests() {
        return this.doneeStatus == DoneeStatus.Active;
    }

    public boolean canRequestMore(int additionalMealsRequested) {
        if (this.doneeType.equals(DoneeType.Individual)) {
            return (this.getQuantityRequested() + additionalMealsRequested) <= this.getMemberCount();
        }
        return true; 
    }

    public void incrementQuantityRequested(int additionalMealsRequested) {
        int currentQuantity = this.getQuantityRequested() != null ? this.getQuantityRequested() : 0;
        this.setQuantityRequested(currentQuantity + additionalMealsRequested);
    }
}