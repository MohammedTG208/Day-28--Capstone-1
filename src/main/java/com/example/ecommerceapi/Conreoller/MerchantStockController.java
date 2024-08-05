package com.example.ecommerceapi.Conreoller;

import com.example.ecommerceapi.Service.MerchantStockService;
import com.example.ecommerceapi.model.MerchantStock;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/mstack")
@RequiredArgsConstructor
public class MerchantStockController {
    private final MerchantStockService mSService;

    @GetMapping("/getall")
    public ResponseEntity getAllMerchantStock(){
        if (mSService.getAllMerchantStocks()==null){
            return ResponseEntity.status(400).body("There are not any merchant stocks");
        }
        return ResponseEntity.status(200).body(mSService.getAllMerchantStocks());
    }

    @PostMapping("/add")
    public ResponseEntity addMerchantStock(@Valid @RequestBody MerchantStock mS, Errors errors){
        if (errors.hasErrors()){
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());
        }
        mSService.addMerchantStock(mS);
        return ResponseEntity.status(201).body("Merchant stock added");
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteMerchantStock(@PathVariable int id){
        if (mSService.deleteMerchantStock(id)){
            return ResponseEntity.status(200).body("Merchant stock deleted");
        }
        return ResponseEntity.status(404).body("Merchant stock not found with id " + id);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity updateMerchantStock(@PathVariable int id, @Valid @RequestBody MerchantStock mS,Errors errors){
        if (errors.hasErrors()){
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());
        }else {
            if (mSService.updateMerchantStock(mS,id)){
                return ResponseEntity.status(200).body("Merchant stock updated");
            }
            return ResponseEntity.status(404).body("Merchant stock not found with id " + id);
        }
    }

}
