package com.example.ecommerceapi.Conreoller;

import com.example.ecommerceapi.Service.UserService;
import com.example.ecommerceapi.model.ProductReviews;
import com.example.ecommerceapi.model.User;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/getall")
    public ResponseEntity getAllUsers() {
        if (userService.getAllUsers()==null){
            return ResponseEntity.status(400).body("There are not users found");
        }
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @PostMapping("/add")
    public ResponseEntity addUser(@Valid @RequestBody User user, Errors errors) {
        if (errors.hasErrors()) {
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());
        }else {
            userService.addUser(user);
            return ResponseEntity.status(201).body("User added successfully");
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteUser(@PathVariable int id) {
        if (userService.removeUser(id)){
            return ResponseEntity.status(200).body("User deleted successfully");
        }
        return ResponseEntity.status(400).body("there is not id like this id "+id);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity updateUser(@PathVariable int id, @Valid @RequestBody User user, Errors errors) {
        if (errors.hasErrors()) {
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());
        }else {
            if (userService.updateUser(id, user)){
                return ResponseEntity.status(200).body("User updated successfully");
            }
            return ResponseEntity.status(400).body("there is not id like this id "+id);
        }
    }

    @PutMapping("/update/to/stock/{mid}/{pid}/{mstockid}/{quantity}")
    public ResponseEntity addToStock(@PathVariable int mid,@PathVariable int pid,@PathVariable int mstockid,@PathVariable int quantity) {
        if (userService.addProductToStock(mid,pid,mstockid,quantity)){
            return ResponseEntity.status(200).body("you add stock successfully");
        }else{
            return ResponseEntity.status(400).body("Check your input and try again");
        }
    }

    @PutMapping("/buy/{uid}/{pid}/{mid}")
    public ResponseEntity buy(@PathVariable int uid,@PathVariable int pid,@PathVariable int mid) {
       if (userService.buyProduct(uid,pid,mid)==1){
           return ResponseEntity.status(400).body("You don't have enough money to buy this product");
       } else if (userService.buyProduct(uid,pid,mid)==2) {
           return ResponseEntity.status(400).body("There are no more products in stock try again later");
       } else if (userService.buyProduct(uid,pid,mid)==3) {
           return ResponseEntity.status(200).body("you buy this product successfully");
       }else {
           return ResponseEntity.status(400).body("Check your input and try again");
       }
    }



    //To add review for product
    @PostMapping("/add/review")
    public ResponseEntity addReview(@Valid @RequestBody ProductReviews productReviews, Errors errors) {
        if (errors.hasErrors()) {
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());
        }else {
            if (userService.addReviewForProduct(productReviews)){
                return ResponseEntity.status(200).body("You added review successfully");
            }else {
                return ResponseEntity.status(400).body("Check your input and try again");
            }
        }
    }

    //delete all relation with all product.
    @DeleteMapping("/delete/for/admin/{uid}/{pid}")
    public ResponseEntity deleteForAdmin(@PathVariable int uid,@PathVariable int pid,@RequestParam(required = false) boolean isDelete) {
        if (userService.checkIsAdmin(uid)) {
            if (userService.deleteProductByAdmin(pid,isDelete) == null) {
                return ResponseEntity.status(400).body("Check your input and try again");
            } else {
                return ResponseEntity.status(200).body(userService.deleteProductByAdmin(pid,isDelete));
            }
        }
        return ResponseEntity.status(400).body("you are not admin");
    }

    @PostMapping("/add/wishlist/{uid}/{pid}")
    public ResponseEntity addWichlist(@PathVariable int uid,@PathVariable int pid) {
        if (userService.addWishList(uid,pid)) {
            return ResponseEntity.status(200).body("You added wishlist successfully");
        }
        return ResponseEntity.status(400).body("Check your input and try again");
    }

    @GetMapping("/get/wishlist/{uid}")
    public ResponseEntity getWishlist(@PathVariable int uid) {
        if (userService.getWishList(uid)==null) {
            return ResponseEntity.status(400).body("Check your input and try again");
        }
        return ResponseEntity.status(200).body(userService.getWishList(uid));
    }
}
