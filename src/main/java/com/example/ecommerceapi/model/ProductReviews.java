package com.example.ecommerceapi.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;
import java.util.Date;

@Data
@AllArgsConstructor
public class ProductReviews {
    @Positive(message = "review id is requirement")
    private int review_id;
    @Positive(message = "product id is requirement")
    private int product_id;
    @Positive(message = "user id is requirement")
    private int user_id;
    @NotNull(message = "user name is requirement")
    private String user_name;
    @NotNull(message = "review text is requirement")
    private String review_text;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate review_date;
    @NotNull(message = "role is requirement")
    @Pattern(regexp = "(Admin|admin|Customer|customer)+$",message = "The role most be Admin or customer")
    private String role;

}
