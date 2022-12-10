package com.zifrasports.zifrasports.controllers;

import com.zifrasports.zifrasports.entities.CategoryEntity;
import com.zifrasports.zifrasports.repository.CategoryRepository;
import com.zifrasports.zifrasports.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class CategoryController {

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    ProductRepository productRepository;

    @GetMapping("/cadastrar-categoria")
    private String openRegisterCategory(CategoryEntity category) {
        return "cadastrar-categoria";
    }

    @PostMapping("/cadastrar-categoria")
    private String saveCategory(CategoryEntity category) {
        categoryRepository.save(category);

        return "cadastrar-categoria";
    }

    @GetMapping("/categoria/{categoryName}")
    private String openCategory(@PathVariable String categoryName, Model model) {

        model.addAttribute("products",productRepository.findByCategory(categoryName));
        model.addAttribute("categories", categoryRepository.findAll());
        model.addAttribute("categoryChosed", categoryName);

        return "categoria";
    }

}
