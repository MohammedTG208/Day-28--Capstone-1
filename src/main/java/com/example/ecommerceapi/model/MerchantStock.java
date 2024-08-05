package com.example.ecommerceapi.model;

import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MerchantStock {
    @Positive(message = "the id is requirement")
    private int id;
    @Positive(message = "the productid is requirement and most be positive number")
    private int productid;
    @Positive(message = "merchantid is requirement and most be positive number")
    private int merchantid;
    @Positive(message = "stock is requirement and most be positive number")
    private int stock;
}
