package com.example.service;

import com.example.CustomExceptions.ContactNotFoundException;
import com.example.DTO.ContactDTO;
import com.example.domain.Contact;
import com.example.repository.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class ContactService {
    @Autowired
    private ContactRepository contactRepository;

    public List<ContactDTO> findAll() {
        var contacts = this.contactRepository.findAll();
        ArrayList<ContactDTO> contactList = new ArrayList<>();
        for (Contact c : contacts) {
            ContactDTO contactDTO = new ContactDTO(c);
            contactList.add(contactDTO);
        }

        return contactList;
    }

    public void save(Contact contact) {
        contactRepository.save(contact);
    }

    public Contact findById(Integer id) throws ContactNotFoundException {
        Optional<Contact> contact = contactRepository.findById(id);
        if (contact.isPresent())
            return contact.get();

        throw new ContactNotFoundException("There is no contact with id " + id + "!");
    }

    public void deleteById(Integer id) throws ContactNotFoundException {
        Long count = contactRepository.countById(id);

        if (count == null || count == 0)
            throw new ContactNotFoundException("There is no contact with id " + id + "!");

        contactRepository.deleteById(id);
    }
}
