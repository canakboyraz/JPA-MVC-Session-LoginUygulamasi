package com.works.controller;

import com.works.entities.Customer;
import com.works.services.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequiredArgsConstructor
public class LoginController {
    final CustomerService customerService;
    final HttpServletRequest request;
    final HttpServletResponse response;
    String error = "";
    String success = "";

    @GetMapping("/")
    private String Login(Model model){
        model.addAttribute("error",error);
        model.addAttribute("success",success);
        error = "";
        success = "";
        return "login";
    }

    @PostMapping("/loginUser")
    private String customerLogin(Customer customer){
        Customer x = customerService.login(customer.getEmail(),customer.getPassword());

        if(x == null){  //kayıt yoksa

            Customer control = customerService.loginUser(customer.getEmail());
            Cookie cookie = new Cookie("customer", ""+x.getCid());
            cookie.setMaxAge(60 * 60);
            response.addCookie(cookie);

            if (control == null){
                error = " Email Hatalı !";
                return "redirect:/";
            }
            else {
                error = " Şifre Hatalı";
                return "redirect:/";
            }
        }
        request.getSession().setAttribute("customer",x);
        return "redirect:/home";

    }

    @GetMapping("/home")
    private String home(){
        return "home";
    }
}
