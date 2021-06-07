package com.lucas.customer.services;

import com.lucas.customer.messaging.CreateCustomerMessage;
import com.lucas.customer.models.Customer;
import com.lucas.customer.repositories.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {
    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public Customer createCustomer(CreateCustomerMessage ccm) {
        Customer customer = new Customer();
        customer.setCredentialsId(ccm.getCredentialsId());
        customer.setFirstName(ccm.getFirstName());
        customer.setMiddleName(ccm.getMiddleName());
        customer.setLastName(ccm.getLastName());
        return this.saveCustomer(customer);
    }

    public Customer saveCustomer(Customer customer) {
        return this.customerRepository.save(customer);
    }

    public Customer getCustomerByCredentialsId(Long id) {
        return this.customerRepository.getCustomerByCredentialsId(id);
    }

    public Customer getCustomerById(Long id) {
        return this.customerRepository.getById(id);
    }
}
