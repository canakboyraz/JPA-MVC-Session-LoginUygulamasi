package com.works.controller;

import com.works.entities.Product;
import com.works.repositories.ProductRepository;
import com.works.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class DashboardController {

    final ProductService productService;
    private final ProductRepository productRepository;

    @GetMapping("/dashboard")
    public String dashboard(Model model){
        model.addAttribute("product",productService.allProduct());
        return "dashboard";
    }

    @PostMapping("/productSave")
    public String productSave(Product product){
        productService.save(product);
        return "redirect:/dashboard";
    }
    @GetMapping("/productDelete/{pid}")
    public String productDelete(@PathVariable Long pid){
        productService.productDelete(pid);
        return "redirect:/dashboard";
    }

}
