package com.learn.adminpanel.controller;

import com.learn.adminpanel.model.ProductMaster;
import com.learn.adminpanel.model.RoleMaster;
import com.learn.adminpanel.model.UserProfile;
import com.learn.adminpanel.repository.ProductMasterRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class ProductController {

    private ProductMasterRepository productMasterRepository;

    @Autowired
    public ProductController(ProductMasterRepository productMasterRepository){
        this.productMasterRepository = productMasterRepository;
    }

    @GetMapping("/showProducts")
    public String showProducts(Model model){
        List<ProductMaster> productsList = productMasterRepository.findAll();

        model.addAttribute("products", productsList);
        return "main-product-list";
    }

    @PostMapping("/deleteProduct")
    public String deleteProduct(@RequestParam("productId") int Id){
        productMasterRepository.deleteById(Id);
        return "redirect:/showProducts";
    }

    @GetMapping("/addProduct")
    public String showFormForAddProduct(Model model) {

        //create model attribute to bind form data
        ProductMaster product = new ProductMaster();

        model.addAttribute("product", product);

        return "modify-product";
    }

    @PostMapping("/saveProduct")
    public String saveProduct(@ModelAttribute("product") ProductMaster product) {

        // Check if this is an existing product
        boolean isNewProduct = product.getProductId() == 0;

        // For existing product: load from DB and update
        if (!isNewProduct) {
            ProductMaster existingProduct = productMasterRepository.findById(product.getProductId())
                    .orElseThrow(() -> new EntityNotFoundException("Product not found"));
        }

        productMasterRepository.save(product);
        return "redirect:/showProducts";
    }

    @GetMapping("/updateProduct")
    public String showFormForUpdate(@RequestParam("productId") int Id, Model model) {

        // Fetch user with roles and login details
        ProductMaster product = productMasterRepository.findById(Id)
                .orElseThrow(() -> new RuntimeException("Profile not found"));

        model.addAttribute("product", product);
        return "modify-product";
    }
}