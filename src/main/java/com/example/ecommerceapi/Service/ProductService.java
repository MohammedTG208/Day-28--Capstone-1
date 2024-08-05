package com.example.ecommerceapi.Service;

import com.example.ecommerceapi.model.Product;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class ProductService {
//    remember to add dependency inject from Category
//    Create endpoint for getting and adding and deleting updating a Product.
    ArrayList<Product> products = new ArrayList<Product>();
    public ArrayList<Product> getProducts() {
        return products.isEmpty()?null:products;
    }

    public void addProduct(Product product) {
        products.add(product);
    }

    public boolean removeProduct(int id) {
        for (int i = 0; i < products.size(); i++) {
            if (products.get(i).getId() == id) {
                products.remove(i);
                return true;
            }
        }
        return false;
    }

    public boolean updateProduct(int id) {
        for (int i = 0; i < products.size(); i++) {
            if (products.get(i).getId() == id) {
                products.set(i, products.get(i));
                return true;
            }
        }
        return false;
    }
}
