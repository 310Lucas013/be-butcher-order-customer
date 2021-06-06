package com.lucas.customer.messaging;

//import lombok.Data;

import java.io.Serializable;
import java.util.List;

//@Data
public class CreateCustomerMessage implements Serializable {
    private long credentialsId;
    private String firstName;
    private String middleName;
    private String lastName;
    private List<String> phoneNumbers;

    public CreateCustomerMessage() {

    }

    public long getCredentialsId() {
        return credentialsId;
    }

    public void setCredentialsId(long credentialsId) {
        this.credentialsId = credentialsId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public List<String> getPhoneNumbers() {
        return phoneNumbers;
    }

    public void setPhoneNumbers(List<String> phoneNumbers) {
        this.phoneNumbers = phoneNumbers;
    }

    public CreateCustomerMessage(long credentialsId, String firstName, String middleName, String lastName, List<String> phoneNumbers) {
        this.credentialsId = credentialsId;
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.phoneNumbers = phoneNumbers;
    }
}
