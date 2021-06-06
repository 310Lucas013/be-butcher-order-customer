package com.lucas.customer.controller;

import com.google.gson.Gson;
import com.lucas.customer.CustomerApplication;
import com.lucas.customer.controllers.CustomerController;
import com.lucas.customer.models.Customer;
import com.lucas.customer.repositories.CustomerRepository;
import com.lucas.customer.services.CustomerService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@ContextConfiguration(classes = CustomerApplication.class)
@WebMvcTest(CustomerController.class)
public class CustomerControllerTests {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private CustomerController customerController;

    @MockBean
    private CustomerService customerService;

    @MockBean
    AmqpTemplate rabbitTemplate;
    @MockBean
    private CustomerRepository customerRepository;

    private final Gson gson = new Gson();

    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void contextLoads() {
        assertThat(customerService).isNotNull();
    }

    @Test
    public void getCustomerById() throws Exception {
        Customer customer = new Customer();
        customer.setId((long) 1);
        customer.setFirstName("Luuk");
        customer.setLastName("Vermeer");
        customer.setCredentialsId((long) 1);

        given(customerService.getCustomerByCredentialsId((long) 1)).willReturn(customer);
        mvc.perform(MockMvcRequestBuilders
                .get("/customers/" + 1)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$").isNotEmpty())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value((long) 1));
    }


}
