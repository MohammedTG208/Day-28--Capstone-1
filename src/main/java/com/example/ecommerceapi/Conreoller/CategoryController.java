package com.example.ecommerceapi.Conreoller;

import com.example.ecommerceapi.Service.CategoryService;
import com.example.ecommerceapi.model.Category;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/category")
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryService categoryService;

    @GetMapping("/get")
    public ResponseEntity getCategory() {
        if (categoryService.getAllCategories()==null){
            return ResponseEntity.status(400).body("there are not any category found");
        }
        return ResponseEntity.ok(categoryService.getAllCategories());
    }
    @PostMapping("/add")
    public ResponseEntity addCategory(@Valid @RequestBody Category category, Errors errors) {
        if (errors.hasErrors()) {
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());
        }else {
            categoryService.addCategory(category);
            return ResponseEntity.status(201).body("Category added successfully");
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteCategory(@PathVariable int id) {
        if (categoryService.removeCategory(id)) {
            return ResponseEntity.status(200).body("Category deleted successfully");
        }
        return ResponseEntity.status(404).body("Category id not found with this id: "+id);
    }
    @PutMapping("/update/{id}")
    public ResponseEntity updateCategory(@PathVariable int id, @Valid @RequestBody Category category, Errors errors) {
        if (errors.hasErrors()) {
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());
        }else {
            if (categoryService.updateCategory(category, id)) {
                return ResponseEntity.status(201).body("Category updated successfully");
            }
            return ResponseEntity.status(404).body("Category id not found with this id: "+id);
        }
    }
}
