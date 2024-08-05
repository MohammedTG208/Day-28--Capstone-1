package com.example.ecommerceapi.Conreoller;

import com.example.ecommerceapi.Service.MerchantService;
import com.example.ecommerceapi.model.Merchant;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/merchant")
@RequiredArgsConstructor
public class MerchantController {
    private final MerchantService merchantService;
    @GetMapping("/getall")
    public ResponseEntity getAllMerchant(){
        if (merchantService.getAllMerchants()==null){
            return ResponseEntity.status(400).body("There are not merchants found");
        }
        return ResponseEntity.status(200).body(merchantService.getAllMerchants());
    }

    @PostMapping("/add")
    public ResponseEntity addMerchant(@Valid @RequestBody Merchant merchant, Errors errors){
        if (errors.hasErrors()){
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());
        }
        merchantService.addMerchant(merchant);
        return ResponseEntity.status(201).body("Merchant added successfully");
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteMerchant(@PathVariable int id){
        if (merchantService.removeMerchant(id)){
            return ResponseEntity.status(200).body("Merchant deleted successfully");
        }
        return ResponseEntity.status(404).body("Merchant not found with id " + id);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity updateMerchant(@PathVariable int id, @RequestBody Merchant merchant,Errors errors){
        if (errors.hasErrors()){
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());
        }else {
            merchantService.updateMerchant(id, merchant);
            return ResponseEntity.status(201).body("Merchant updated successfully");
        }
    }

    @PutMapping("/discount/{merchantID}/{pID}/{discount}")
    public ResponseEntity updateDiscount(@PathVariable int merchantID,@PathVariable int pID,@PathVariable int discount) {
        if (merchantService.discountByMerchant(merchantID, pID, discount)){
            return ResponseEntity.status(200).body("You add the discount successfully");
        }
        return ResponseEntity.status(400).body("Check your input and try again");
    }
}
