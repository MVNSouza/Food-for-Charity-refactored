package com.foodforcharity.app.domain.entity;

import com.foodforcharity.app.domain.constant.DonorStatus;
import com.foodforcharity.app.domain.valueobject.Address;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Getter
@Setter
@Entity
@DiscriminatorValue("Donor")
public class Donor extends Person {
    private static final long serialVersionUID = 1L;

    @Embedded
    private Address address;

    @Column(name = "DONOR_NAME")
    @NotNull
    private String donorName;

    @NotNull
    @Email
    @Column(unique = true)
    private String email;

    @Column(name = "NUMBER_OF_RATING")
    @NotNull
    private Integer numberOfRating = 0;

    @Column(name = "PHONE_NUMBER")
    @NotNull
    private String phoneNumber;

    @NotNull
    private Integer rating = 0;

    @NotNull
    private Integer discountApplied = 0;

    @Column(name = "DONOR_STATUS")
    @Enumerated(EnumType.STRING)
    @NotNull
    private DonorStatus donorStatus;

    // Mantemos apenas a lista de Foods, pois o Doador é o "Dono" do seu cardápio
    @OneToMany(mappedBy = "donor", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Food> foods = new ArrayList<>();

    public Food addFood(Food food) {
        this.foods.add(food);
        food.setDonor(this);
        return food;
    }

    public void removeFood(Food food) {
        this.foods.remove(food);
        food.setDonor(null);
    }

    @Override
    public Optional<String> getStatus() {
        return Optional.of(donorStatus.name());
    }

    public boolean isEligibleForDonations() {
        return this.donorStatus != DonorStatus.Initial && this.donorStatus != DonorStatus.Suspended;
    }
}