package com.example.ecommerceapi.Conreoller;

import com.example.ecommerceapi.Service.ProductService;
import com.example.ecommerceapi.model.Product;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/product")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @GetMapping("/get")
    public ResponseEntity getAllProduct() {
        if (productService.getProducts()==null){
            return ResponseEntity.status(400).body("there are not any products yet");
        }
        return ResponseEntity.status(200).body(productService.getProducts());
    }
    @PostMapping("/add")
    public ResponseEntity addProduct(@Valid @RequestBody Product product, Errors errors) {
        if (errors.hasErrors()) {
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());
        }
        productService.addProduct(product);
        return ResponseEntity.status(201).body("Product added successfully");
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteProduct(@PathVariable int id) {
        if (productService.removeProduct(id)){
            return ResponseEntity.status(200).body("Product deleted successfully");
        }
        return ResponseEntity.status(404).body("Product not found with id " + id);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity updateProduct(@PathVariable int id,@Valid @RequestBody Product product,Errors errors) {
        if (errors.hasErrors()) {
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());
        }else {
            if (productService.updateProduct(id)){
                return ResponseEntity.status(200).body("Product updated successfully");
            }else {
                return ResponseEntity.status(404).body("Product not found with id " + id);
            }
        }
    }
}
