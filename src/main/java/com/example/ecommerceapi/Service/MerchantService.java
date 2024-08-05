package com.example.ecommerceapi.Service;

import com.example.ecommerceapi.model.Merchant;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class MerchantService {
    private final ProductService productService1;
    private final MerchantStockService merchantStockService1;
    ArrayList<Merchant> merchants = new ArrayList<>();
    public ArrayList<Merchant> getAllMerchants() {
        return merchants.isEmpty()?null:merchants;
    }

    public void addMerchant(Merchant merchant) {
        merchants.add(merchant);
    }

    public boolean removeMerchant(int id) {
        for (int i = 0; i < merchants.size(); i++) {
            if (merchants.get(i).getId() == id) {
                merchants.remove(i);
                return true;
            }
        }
        return false;
    }

    public boolean updateMerchant(int id, Merchant merchant) {
        for (int i = 0; i < merchants.size(); i++) {
            if (merchants.get(i).getId() == id) {
                merchants.set(i, merchant);
                return true;
            }
        }
        return false;
    }

    //3
    public boolean discountByMerchant(int merchantID,int pID,int discount){
        for (int i=0; i<merchants.size(); i++) {

            if (merchants.get(i).getId()==merchantID){

                for (int j=0; j<merchants.size(); j++) {

                    if (merchantStockService1.getAllMerchantStocks().get(j).getProductid()==pID && merchantStockService1.getAllMerchantStocks().get(j).getMerchantid()==merchantID){

                        for (int k=0; k<productService1.getProducts().size(); k++) {

                            if (productService1.getProducts().get(k).getId()==pID){
                                double discountAmount=productService1.getProducts().get(k).getPrice()*(discount /100);
                                productService1.getProducts().get(k).setPrice(productService1.getProducts().get(k).getPrice()-discountAmount);
                                return true;
                            }
                        }
                    }
                }
            }
        }
        return false;
    }
}
