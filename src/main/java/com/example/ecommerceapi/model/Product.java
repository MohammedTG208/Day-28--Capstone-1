package com.example.ecommerceapi.model;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Product {
    @Positive(message = "id is requirement and be positive number")
    private int id;
    @NotNull(message = "name is requirement")
    @Size(min = 3,message = "name most be more than 3 length long")
    private String name;
    @Positive(message = "price is requirement and most be positive")
    private double price;
    @Positive(message = "categoryID is requirement and most be positive")
    private int categoryID;
}
