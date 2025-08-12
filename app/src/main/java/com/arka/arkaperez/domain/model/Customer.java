package com.arka.arkaperez.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.regex.Pattern;

/**
 * Customer Domain Entity
 * Represents a customer in the domain layer
 * Following Domain-Driven Design principles
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Customer {
    
    private Long id;
    private String name;
    private String lastName;
    private String email;
    private String phone;
    private LocalDate birthDate;
    private String address;
    private String city;
    private String country;
    private boolean active;
    
    // Email validation pattern
    private static final Pattern EMAIL_PATTERN = 
        Pattern.compile("^[A-Za-z0-9+_.-]+@([A-Za-z0-9.-]+\\.[A-Za-z]{2,})$");
    
    /**
     * Domain validation for email format
     */
    public boolean isValidEmail() {
        return email != null && EMAIL_PATTERN.matcher(email).matches();
    }
    
    /**
     * Domain method to get full name
     */
    public String getFullName() {
        return String.format("%s %s", 
            name != null ? name : "", 
            lastName != null ? lastName : "").trim();
    }
    
    /**
     * Domain validation for required fields
     */
    public boolean isValid() {
        return name != null && !name.trim().isEmpty() 
            && email != null && isValidEmail();
    }
    
    /**
     * Check if customer has complete profile
     */
    public boolean hasCompleteProfile() {
        return name != null && email != null && phone != null 
            && address != null && city != null;
    }
    
    /**
     * Domain method to activate customer
     */
    public void activate() {
        this.active = true;
    }
    
    /**
     * Domain method to deactivate customer
     */
    public void deactivate() {
        this.active = false;
    }
}
