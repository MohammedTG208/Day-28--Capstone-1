package com.example.ecommerceapi.model;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Category {
    @Positive(message = "id is requirement and be Positive number")
    private int id;
    @NotNull(message = "name is requirement")
    @Size(min = 3,message = "name most be more than 3 length long")
    private String name;
}
