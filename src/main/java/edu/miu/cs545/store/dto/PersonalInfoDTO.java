package edu.miu.cs545.store.dto;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

public class PersonalInfoDTO {
    @NotEmpty
    @Length(min = 2, max = 100)
    private String name;

    @NotEmpty
    @Length(min = 3, max = 100)
    @Email
    private String email;

    @NotEmpty
    @Length(min = 2, max = 20)
    private String phone;

    @NotEmpty
    @Length(min = 2, max = 100)
    private String street;

    @NotEmpty
    @Length(min = 2, max = 100)
    private String city;

    @NotEmpty
    @Length(min = 2, max = 100)
    private String state;

    @NotEmpty
    @Length(min = 2, max = 10)
    private String zip;

    public PersonalInfoDTO() {
    }

    public PersonalInfoDTO(String name, String email, String phone, String street, String city, String state, String zip) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.street = street;
        this.city = city;
        this.state = state;
        this.zip = zip;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }
}
