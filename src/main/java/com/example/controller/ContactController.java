package com.example.controller;

import com.example.CustomExceptions.ContactNotFoundException;
import com.example.DTO.ContactDTO;
import com.example.domain.Contact;
import com.example.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@CrossOrigin(origins = "http://localhost:8080")
@Controller
public class ContactController {
    @Autowired
    private ContactService contactService;

    @GetMapping("/contacts")
    public String showContactList(Model model) {
        List<ContactDTO> contactList = contactService.findAll();
        model.addAttribute("contactList", contactList);

        return "contacts";
    }

    @GetMapping("/contacts/new")
    public String showNewContactForm(Model model) {
        model.addAttribute("contact", new Contact());
        model.addAttribute("pageTitle", "Add New Contact");
        return "contactform";
    }

    @PostMapping(value = "/contacts/save", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public String saveContact(@RequestParam(required = false, name="id")Integer id, @RequestParam(required = true, value = "picture") MultipartFile multipartFile,
                              @RequestParam(name = "name") String name, @RequestParam(name = "address") String address) {
        try {
            Contact contact = new Contact();
            byte[] picture = multipartFile.getBytes();
            if (id != null) {
                contact.setId(id);
            }
            contact.setPicture(picture);
            contact.setName(name);
            contact.setAddress(address);
            contactService.save(contact);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return "redirect:/contacts";
    }

    @GetMapping("/contacts/edit/{id}")
    public String showEditForm(@PathVariable("id") Integer id, Model model) {
        try {
            Contact contact = contactService.findById(id);
            ContactDTO contactDTO = new ContactDTO(contact);
            model.addAttribute("contact", contactDTO);
            model.addAttribute("pageTitle", "Edit Contact #" + id);
            return "contactform";
        } catch (ContactNotFoundException e) {
            e.printStackTrace();
            return "redirect:/contacts";
        }
    }

    @GetMapping("/contacts/delete/{id}")
    public String deleteContact(@PathVariable("id") Integer id) {
        try {
            contactService.deleteById(id);
        } catch (ContactNotFoundException e) {
            e.printStackTrace();
        }
        return "redirect:/contacts";
    }

}
