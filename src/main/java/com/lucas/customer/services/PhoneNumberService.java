package com.lucas.customer.services;

import com.lucas.customer.repositories.PhoneNumberRepository;
import org.springframework.stereotype.Service;

@Service
public class PhoneNumberService {
    private final PhoneNumberRepository phoneNumberRepository;

    public PhoneNumberService(PhoneNumberRepository phoneNumberRepository) {
        this.phoneNumberRepository = phoneNumberRepository;
    }
}
