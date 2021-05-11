package com.lucas.customer.controllers;

import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.lucas.customer.config.HibernateProxyTypeAdapter;
import com.lucas.customer.models.Customer;
import com.lucas.customer.models.PhoneNumber;
import com.lucas.customer.services.CustomerService;
import com.lucas.customer.services.PhoneNumberService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/customers")
public class CustomerController {
    private final CustomerService customerService;
    private final PhoneNumberService phoneNumberService;

    public CustomerController(CustomerService customerService,
                              PhoneNumberService phoneNumberService) {
        this.customerService = customerService;
        this.phoneNumberService = phoneNumberService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<String> getCustomer(@PathVariable("id") Long id) {
        Gson gson = initiateGson();
        Customer customer = customerService.getCustomerByCredentialsId(id);
        if (customer == null) {
            return new ResponseEntity<>("Customer not found", HttpStatus.NOT_FOUND);
        } else {
            String result = gson.toJson(customer);
            return new ResponseEntity<>(result, HttpStatus.OK);
        }
    }

    @PutMapping
    public ResponseEntity<String> updateCustomer(@RequestBody Customer customer) {
        System.out.println("Hello");
        System.out.println(customer.toString());
        System.out.println(customer);
        List<PhoneNumber> phoneNumbers = new ArrayList<>();
        for(PhoneNumber phoneNumber: customer.getPhoneNumbers()) {
            if(phoneNumber.getId() == 0) {
                System.out.println("Enters this");
                PhoneNumber p = new PhoneNumber();
                p.setCustomer(customer);
                p.setPhoneNumber(phoneNumber.getPhoneNumber());
                PhoneNumber savedPhoneNumber = phoneNumberService.savePhoneNumber(p);
                System.out.println(savedPhoneNumber);
                phoneNumbers.add(savedPhoneNumber);
            } else {
                phoneNumbers.add(phoneNumber);
            }
        }
        System.out.println(phoneNumbers);
        Customer updatedCustomer = customerService.getCustomerById(customer.getId());
        updatedCustomer.setFirstName(customer.getFirstName());
        updatedCustomer.setMiddleName(customer.getMiddleName());
        updatedCustomer.setLastName(customer.getLastName());
        updatedCustomer.setLocationId(customer.getLocationId());
        updatedCustomer.setPhoneNumbers(phoneNumbers);
        Customer savedCustomer = customerService.saveCustomer(updatedCustomer);
        System.out.println(savedCustomer);
        Gson gson = initiateGson();
        String result = gson.toJson(savedCustomer);
        System.out.println(result);
        return new ResponseEntity<String>(result, HttpStatus.ACCEPTED);
    }

    private Gson initiateGson() {
        GsonBuilder b = new GsonBuilder();
        b.registerTypeAdapterFactory(HibernateProxyTypeAdapter.FACTORY)
                .excludeFieldsWithModifiers(Modifier.TRANSIENT)
                .setExclusionStrategies(new ExclusionStrategy() {
                    @Override
                    public boolean shouldSkipField(FieldAttributes f) {
                        boolean exclude = false;
                        try {
                            exclude = EXCLUDE.contains(f.getName());
                        } catch (Exception ignore) {
                        }
                        return exclude;
                    }

                    @Override
                    public boolean shouldSkipClass(Class<?> clazz) {
                        return false;
                    }
                });
        return b.create();
    }

    private static final List<String> EXCLUDE = new ArrayList<>() {{
        add("customer");
    }};
}
