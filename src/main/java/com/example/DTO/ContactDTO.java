package com.example.DTO;

import com.example.domain.Contact;

import javax.persistence.Lob;
import java.util.Base64;

public class ContactDTO {
    private Integer id;
    private String picture;
    private String name;
    private String address;

    public ContactDTO(Contact contact) {
        this.id = contact.getId();
        this.name = contact.getName();
        this.address = contact.getAddress();
        this.picture = Base64.getEncoder().encodeToString(contact.getPicture());
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
