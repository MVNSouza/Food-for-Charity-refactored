package com.foodforcharity.app.domain.entity;

import com.foodforcharity.app.domain.convertor.BooleanCharacterConverter;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * The persistent class for the REQUEST database table.
 */
@Getter
@Setter // Trocado @Data por Getter e Setter para evitar loop infinito em relacionamentos bidirecionais
@Entity
public class Request implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "DISCOUNT_APPLIED")
    @NotNull
    private Integer discountApplied;

    @Column(name = "FINAL_PRICE")
    @NotNull
    private Integer finalPrice;

    @Column(name = "IS_ACTIVE")
    @Convert(converter = BooleanCharacterConverter.class)
    @NotNull
    private Boolean isActive;

    @Column(name = "IS_RATED")
    @Convert(converter = BooleanCharacterConverter.class)
    @NotNull
    private Boolean isRated;

    @Column(name = "REQUEST_TIME")
    @NotNull
    private Date requestTime;

    // bi-directional many-to-one association to Complaint
    @OneToMany(mappedBy = "request", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @Fetch(value = FetchMode.SUBSELECT)
    private List<Complaint> complaints;

    // bi-directional many-to-one association to Donor
    @ManyToOne
    @NotNull
    private Donor donor;

    // bi-directional many-to-one association to Donee
    @ManyToOne
    @NotNull
    private Donee donee;

    // bi-directional many-to-one association to SubRequest
    @OneToMany(mappedBy = "request", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    @Fetch(value = FetchMode.SUBSELECT)
    private List<SubRequest> subRequests;

    public Request(){
        complaints = new ArrayList<>();
        subRequests = new ArrayList<>();
    }

    public Complaint addComplaint(Complaint complaint) {
        getComplaints().add(complaint);
        complaint.setRequest(this);

        return complaint;
    }

    public Complaint removeComplaint(Complaint complaint) {
        getComplaints().remove(complaint);

        return complaint;
    }

    public SubRequest addSubRequest(SubRequest subRequest) {
       getSubRequests().add(subRequest);

        subRequest.setRequest(this);
        return subRequest;
    }

    public SubRequest removeSubRequest(SubRequest subRequest) {
        getSubRequests().remove(subRequest);

        return subRequest;
    }

    @PreRemove
    public void preRemove() {
        // donor.removeRequest(this); <-- LINHA APAGADA! O Donor não precisa mais gerenciar isso.
        
        // Mantemos o donee porque você ainda não refatorou a classe Donee para remover a lista de requests (como fizemos no Donor)
        if (donee != null) {
            donee.removeRequest(this); 
        }
    }

}