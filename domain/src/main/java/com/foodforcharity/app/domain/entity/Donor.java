package com.foodforcharity.app.domain.entity;

import com.foodforcharity.app.domain.constant.DonorStatus;

import lombok.Data;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Date;
import com.foodforcharity.app.domain.valueobject.RequestedItem;

/**
 * The persistent class for the DONOR database table.
 */
@Data
@Entity
@DiscriminatorValue("Donor")
public class Donor extends Person {
    private static final long serialVersionUID = 1L;

    @Column(name = "ADDRESS_DESCRIPTION")
    @NotNull
    private String addressDescription;

    @NotNull
    private String city;

    @NotNull
    private String country;

    @Column(name = "DONOR_NAME")
    @NotNull
    private String donorName;

    @NotNull
    @Email
    @Column(unique = true)
    private String email;

    @Column(name = "NUMBER_OF_RATING")
    @NotNull
    private Integer numberOfRating;

    @Column(name = "PHONE_NUMBER")
    @NotNull
    private String phoneNumber;

    @NotNull
    private Integer rating;

    @NotNull
    private Integer discountApplied;

    // bi-directional many-to-one association to DonorStatus
    @Column(name = "DONOR_STATUS")
    @Enumerated(EnumType.STRING)
    @NotNull
    private DonorStatus donorStatus;

    // bi-directional many-to-one association to Food
    @OneToMany(mappedBy = "donor", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    @Fetch(value = FetchMode.SUBSELECT)
    private List<Food> foods;

    // bi-directional many-to-one association to Request
    @OneToMany(mappedBy = "donor", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    @Fetch(value = FetchMode.SUBSELECT)
    private List<Request> requests;

    public Donor(){
        foods = new ArrayList<Food>();
        requests = new ArrayList<Request>();
    }

    public Food addFood(Food food) {
        getFoods().add(food);
        food.setDonor(this);

        return food;
    }

    public void removeFood(Food food) {
        getFoods().remove(food);
    }

    public Request addRequest(Request request) {
        getRequests().add(request);
        request.setDonor(this);

        return request;
    }

    public void removeRequest(Request request) {
        getRequests().remove(request);
    }

    @Override
    public Optional<String> getStatus() {
        return Optional.of(donorStatus.name());
    }


    // Na sua classe Donor.java (Adicione estes métodos)

public boolean isEligibleForDonations() {
    return this.donorStatus != DonorStatus.Initial && this.donorStatus != DonorStatus.Suspended;
}

public Request generateRequest(Donee donee, List<RequestedItem> items) {
    Request request = new Request();
    request.setRequestTime(new Date());
    request.setIsActive(true);
    request.setIsRated(false);
    
    // Assumindo que getDiscountApplied retorna um Double ou Integer, lidamos de forma segura para evitar bug de divisão int
    double discountPercentage = this.discountApplied != null ? this.discountApplied.doubleValue() : 0.0;
    request.setDiscountApplied((int) discountPercentage);

    double discountMultiplier = (100.0 - discountPercentage) / 100.0;
    
    int totalOriginalPrice = items.stream().mapToInt(RequestedItem::calculateTotalOriginalPrice).sum();
    request.setFinalPrice((int) (totalOriginalPrice * discountMultiplier));
    
    // Gerar SubRequests
    List<SubRequest> subRequests = items.stream().map(item -> {
        SubRequest sub = new SubRequest();
        sub.setRequest(request);
        sub.setFood(item.food());
        sub.setQuantity(item.quantity());
        
        // Corrigido o bug da divisão de inteiro no preço individual
        int subPrice = (int) (item.food().getPrice() * discountMultiplier);
        sub.setPriceAtPurchase(subPrice);
        
        return sub;
    }).toList(); // ou .collect(Collectors.toList()) se for Java < 16
    
    request.setSubRequests(subRequests);
    
    // Vincula o pedido ao Doador e ao Donatário
    this.addRequest(request);
    donee.addRequest(request);
    
    return request;
}

//	@PreRemove
//	public void preRemove(){
//		foods.forEach(food -> food.preRemove());
//		requests.forEach(request -> {
//			request.preRemove();
//		});
//		requests.clear();
//	}

}