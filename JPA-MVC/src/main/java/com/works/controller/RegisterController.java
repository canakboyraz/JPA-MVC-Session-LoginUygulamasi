package com.works.controller;

import com.works.entities.Customer;
import com.works.services.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import javax.jws.WebParam;

@Controller
@RequiredArgsConstructor
public class RegisterController {

    final CustomerService customerService;
    String error ="";
    String success ="";

    @GetMapping("/register")
    private String register(Model model){
        model.addAttribute("error",error);
        model.addAttribute("success",success);
        error = "";
        success ="";
        Customer c = customerService.login("canakboyraz@gmail.com","12345");
        System.out.println(c);
        return "register";

    }

    @PostMapping("/registerUser")
    private String customerRegister(Customer customer){
        Customer c = customerService.save(customer);
        if (c != null && c.getCid() == null){
            error = customer.getEmail() + "Bu mail adresi daha önceden kayıtlı";
        } else if (c != null && c.getCid() > 0) {
            success = customer.getName() + " Kayıt Başarılı";
        }
        return "redirect:/";
    }


}
