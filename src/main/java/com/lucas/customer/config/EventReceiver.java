package com.lucas.customer.config;

import com.google.gson.Gson;
import com.lucas.customer.messaging.CreateCustomerMessage;
import com.lucas.customer.models.Customer;
import com.lucas.customer.models.PhoneNumber;
import com.lucas.customer.services.CustomerService;
import com.lucas.customer.services.PhoneNumberService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class EventReceiver {

    @Autowired
    private CustomerService customerService;
    @Autowired
    private PhoneNumberService phoneNumberService;

    private Logger log = LoggerFactory.getLogger(EventReceiver.class);

    @RabbitListener(queues = "create-customer-queue")
    public void receive(String message) {
        Gson gson = new Gson();
        System.out.println("received the event!");
        System.out.println(message);
        CreateCustomerMessage ccm = gson.fromJson(message, CreateCustomerMessage.class);
        System.out.println(ccm);

        Customer customer = customerService.createCustomer(ccm);
        List<PhoneNumber> phoneNumbers = phoneNumberService.createPhoneNumbers(ccm.getPhoneNumbers(), customer);
        System.out.println(customer);
        System.out.println(phoneNumbers);

//        log.info("Received event in customer: {}", message.getBody());
//        CreateCustomerMessage ccm = gson.fromJson(new String(message.getBody()), CreateCustomerMessage.class);
//        System.out.println(ccm);

        // Convert to object.
        // Do with it whatever you please.
    }
}

