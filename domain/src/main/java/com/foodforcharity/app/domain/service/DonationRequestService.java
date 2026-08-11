package com.foodforcharity.app.domain.service;

import com.foodforcharity.app.domain.entity.Donee;
import com.foodforcharity.app.domain.entity.Donor;
import com.foodforcharity.app.domain.entity.Request;
import com.foodforcharity.app.domain.entity.SubRequest;
import com.foodforcharity.app.domain.valueobject.RequestedItem;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DonationRequestService {

    @Transactional
    public Request generateRequest(Donor donor, Donee donee, List<RequestedItem> items) {
        if (!donor.isEligibleForDonations()) {
            throw new IllegalStateException("O doador não está elegível para realizar doações no momento.");
        }

        Request request = new Request();
        request.setRequestTime(new Date());
        request.setIsActive(true);
        request.setIsRated(false);
        
        double discountPercentage = donor.getDiscountApplied() != null ? donor.getDiscountApplied().doubleValue() : 0.0;
        request.setDiscountApplied((int) discountPercentage);

        double discountMultiplier = (100.0 - discountPercentage) / 100.0;
        
        int totalOriginalPrice = items.stream().mapToInt(RequestedItem::calculateTotalOriginalPrice).sum();
        request.setFinalPrice((int) (totalOriginalPrice * discountMultiplier));
        
        List<SubRequest> subRequests = items.stream().map(item -> {
            SubRequest sub = new SubRequest();
            sub.setRequest(request);
            
            // Usando getFood() e getQuantity() caso o RequestedItem seja um objeto normal
            sub.setFood(item.getFood()); 
            sub.setQuantity(item.getQuantity());
            
            int subPrice = (int) (item.getFood().getPrice() * discountMultiplier);
            sub.setPriceAtPurchase(subPrice);
            
            return sub;
        }).collect(Collectors.toList()); 
        
        request.setSubRequests(subRequests);
        
        // --- CORREÇÃO AQUI ---
        // Como o Donor não tem mais a lista bidirecional, informamos o doador diretamente ao pedido
        request.setDonor(donor); 
        
        // Mantido pois o Donee ainda usa a estrutura de listas
        donee.addRequest(request);
        
        return request;
    }
}