package com.lucas.customer.services;

import com.lucas.customer.models.Customer;
import com.lucas.customer.models.PhoneNumber;
import com.lucas.customer.repositories.PhoneNumberRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PhoneNumberService {
    private final PhoneNumberRepository phoneNumberRepository;

    public PhoneNumberService(PhoneNumberRepository phoneNumberRepository) {
        this.phoneNumberRepository = phoneNumberRepository;
    }

    public List<PhoneNumber> createPhoneNumbers(List<String> phoneNumbers, Customer customer) {
        List<PhoneNumber> result = new ArrayList<PhoneNumber>();
        for(String pString: phoneNumbers) {
            PhoneNumber ph = new PhoneNumber();
            ph.setPhoneNumber(pString);
            ph.setCustomer(customer);
            result.add(this.savePhoneNumber(ph));
        }
        return result;
    }

    public PhoneNumber savePhoneNumber(PhoneNumber phoneNumber) {
        return this.phoneNumberRepository.save(phoneNumber);
    }
}
