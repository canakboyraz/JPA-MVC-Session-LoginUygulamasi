package com.works.config;

import com.works.entities.Customer;
import com.works.repositories.NoteRepository;
import com.works.services.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Configuration;


import javax.servlet.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.logging.LogRecord;

@Configuration
@RequiredArgsConstructor
public class FilterConfig implements Filter {
    final CustomerService customerService;

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse res = (HttpServletResponse) servletResponse;
        String url = req.getRequestURI();
        String freeUrls[] = {"/","/loginUser","/register","/registerUser","/note","/noteSave","/noteDelete"};

        boolean loginStatus = true;
        for(String item : freeUrls){
            if(url.equals(item)){
                loginStatus = false;
                break;
            }
        }
        if(loginStatus){
            if(req.getCookies() != null){
                Cookie[] cookies = req.getCookies();
                for(Cookie cookie :cookies){
                    if (cookie.getName().equals("customer")){
                        int val = Integer.parseInt(cookie.getValue());
                        break;
                    }
                }
            }
            boolean status = req.getSession().getAttribute("customer") == null;
            if(status){
                res.sendRedirect("/");
            }else{
                Customer c = (Customer) req.getSession().getAttribute("customer");
                req.setAttribute("customer",c);
                filterChain.doFilter(req,res);
            }

        }
        else {
            filterChain.doFilter(req,res);
        }
    }
}