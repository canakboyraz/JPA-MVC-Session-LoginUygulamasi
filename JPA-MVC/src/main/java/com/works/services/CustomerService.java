package com.works.services;

import com.works.entities.Customer;
import com.works.repositories.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomerService {

    public Customer login(String email,String password){
        Optional<Customer> optionalCustomer = customerRepository.findByEmailEqualsIgnoreCaseAndPasswordEquals(email, password);
        if (optionalCustomer.isPresent()){
            return optionalCustomer.get();
        }
        return null;
    }

    public Customer loginUser(String email){
        Optional<Customer> optionalCustomer = customerRepository.findByEmailEqualsIgnoreCase(email);
        if (optionalCustomer.isPresent()){
            return optionalCustomer.get();
        }
        return null;
    }

    final CustomerRepository customerRepository;
    public Customer save( Customer customer){
        customer.setStatus(true);
        try {
            return customerRepository.save(customer);
        }
        catch (DataIntegrityViolationException ex){
            System.err.println("Email error" + ex);
            return customer;
        }catch (Exception sql){
            return null;
        }
    }
}
