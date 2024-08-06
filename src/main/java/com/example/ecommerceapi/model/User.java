package com.example.ecommerceapi.model;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class User {
//• email (must not be empty, must be valid email).
//• role (must not be empty, have to be in ( “Admin”,”Customer”)).
//• balance (must not be empty, have to be positive).

    @Positive(message = "the id is requirement and most be positive")
    private int id;
    @NotNull(message = "User name must not be empty")
    @Size(message = "User name have to be more than 5 length long")
    private String username;
    @Pattern(regexp = "^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{8,}$" ,message = "Minimum eight characters, at least one letter, one number and one special character")
    private String password;
    @Email(message = "enter valid email")
    private String email;
    @Pattern(regexp = "(Admin|admin|Customer|customer)+$",message = "The role most be Admin or customer")
    private String role;
    @Positive(message = "balance must not be empty, have to be positive")
    private double balance;
    private ArrayList<Product> productsCart;

}
