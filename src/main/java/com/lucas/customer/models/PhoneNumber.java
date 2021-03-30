package com.lucas.customer.models;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "Phonenumber")
public class PhoneNumber {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;
    @Column(name = "phone_number")
    private String phoneNumber;
    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    public PhoneNumber() {

    }

}
