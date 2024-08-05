package com.example.ecommerceapi.Service;

import com.example.ecommerceapi.model.Category;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class CategoryService {
    ArrayList<Category> categories = new ArrayList<Category>();

    public ArrayList<Category> getAllCategories() {
        return categories.isEmpty()?null:categories;
    }

    public void addCategory(Category category) {
        categories.add(category);
    }

    public boolean removeCategory(int id) {
        for (int i = 0; i < categories.size(); i++) {
            if (categories.get(i).getId() == id) {
                categories.remove(i);
                return true;
            }
        }
        return false;
    }

    public boolean updateCategory(Category category,int id) {
        for (int i = 0; i < categories.size(); i++) {
            if (categories.get(i).getId() == id) {
                categories.set(i, category);
                return true;
            }
        }
        return false;
    }
}
