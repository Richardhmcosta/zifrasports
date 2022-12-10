package com.zifrasports.zifrasports.controllers;

import com.zifrasports.zifrasports.entities.CategoryEntity;
import com.zifrasports.zifrasports.entities.ProductEntity;
import com.zifrasports.zifrasports.repository.CategoryRepository;
import com.zifrasports.zifrasports.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Controller
public class ProductController {

    private static String uploadPath = "/home/pc/Área de Trabalho/zifrasports/src/main/resources/static/uploads/";

    @Autowired
    ProductRepository productRepository;

    @Autowired
    CategoryRepository categoryRepository;

    @GetMapping("/")
    private String openIndex(Model model) {
        model.addAttribute("categories", categoryRepository.findAll());
        model.addAttribute("products", productRepository.findAll());

        return "index";
    }

    @GetMapping("/cadastrar-produto")
    private String openRegisterProduct(ProductEntity product, Model model) {
        model.addAttribute("categories", categoryRepository.findAll());

        return "cadastrar-produto";
    }

    @PostMapping("/cadastrar-produto")
    private String registerProduct(ProductEntity product, @RequestParam("file") MultipartFile file) {

        productRepository.save(product);

        try {
            if (!file.isEmpty()) {
                byte[] bytes = file.getBytes();
                Path path = Paths.get(uploadPath + String.valueOf(product.getId()) + file.getOriginalFilename());
                Files.write(path, bytes);

                product.setImage(String.valueOf(product.getId()) + file.getOriginalFilename());

            }
        } catch (IOException e) {
            e.printStackTrace();

            return "cadastrar-produto";
        }


        productRepository.save(product);

        return "cadastrar-produto";
    }

    @GetMapping("/contato")
    private String openContato() {
        return "contato";
    }

    @GetMapping("/categoria")
    private String openCategoria() {
        return "categoria";
    }
}
