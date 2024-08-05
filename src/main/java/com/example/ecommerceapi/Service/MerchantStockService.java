package com.example.ecommerceapi.Service;

import com.example.ecommerceapi.model.MerchantStock;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class MerchantStockService {

    ArrayList<MerchantStock> merchantStocks = new ArrayList<MerchantStock>();
    public ArrayList<MerchantStock> getAllMerchantStocks() {
        return merchantStocks.isEmpty()?null:merchantStocks;
    }
    public void addMerchantStock(MerchantStock merchantStock) {
        merchantStocks.add(merchantStock);
    }

    public boolean deleteMerchantStock(int id) {
        for (MerchantStock merchantStock : merchantStocks) {
            if (merchantStock.getId() == id) {
                merchantStocks.remove(merchantStock);
                return true;
            }
        }
        return false;
    }

    public boolean updateMerchantStock(MerchantStock merchantStock,int id) {
        for(int i = 0; i < merchantStocks.size(); i++) {
            if (merchantStocks.get(i).getId() == id) {
                merchantStocks.set(i,merchantStock);
                return true;
            }
        }
        return false;
    }
}
