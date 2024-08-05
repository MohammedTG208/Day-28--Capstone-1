package com.example.ecommerceapi.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class WishList {
    private int customerId;
    private String p_name;
    private int quantity;
    private double price;

}
