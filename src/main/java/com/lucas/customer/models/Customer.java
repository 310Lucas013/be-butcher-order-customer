package com.lucas.customer.models;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@Table(name = "Customer")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    protected long id;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "middle_name")
    private String middleName;
    @OneToMany(mappedBy = "customer", targetEntity = PhoneNumber.class)
    private List<PhoneNumber> phoneNumbers;


    public Customer() {

    }

}